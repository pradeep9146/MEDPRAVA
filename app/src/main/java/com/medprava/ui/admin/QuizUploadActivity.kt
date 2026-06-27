package com.medprava.ui.admin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.medprava.databinding.ActivityQuizUploadBinding
import com.medprava.domain.model.Quiz
import com.medprava.domain.model.Question
import com.medprava.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject
import com.google.firebase.firestore.FirebaseFirestore

@AndroidEntryPoint
class QuizUploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizUploadBinding
    @Inject
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.uploadButton.setOnClickListener {
            uploadQuiz()
        }
    }

    private fun uploadQuiz() {
        val title = binding.quizTitleInput.text.toString().trim()
        val description = binding.quizDescriptionInput.text.toString().trim()
        val subject = binding.subjectSpinner.selectedItem.toString()
        val academicYear = binding.academicYearSpinner.selectedItem.toString()
        val duration = binding.durationInput.text.toString().toIntOrNull() ?: 30
        val passingScore = binding.passingScoreInput.text.toString().toIntOrNull() ?: 60

        if (title.isEmpty() || description.isEmpty()) {
            showError("All fields are required")
            return
        }

        binding.uploadButton.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val quizId = UUID.randomUUID().toString()
                val quiz = Quiz(
                    quizId = quizId,
                    title = title,
                    description = description,
                    subject = subject,
                    academicYear = academicYear,
                    duration = duration,
                    passingScore = passingScore,
                    totalQuestions = 0,
                    isActive = true
                )

                firestore.collection("quizzes").document(quizId).set(quiz)
                    .addOnSuccessListener {
                        binding.progressBar.visibility = View.GONE
                        binding.uploadButton.isEnabled = true
                        showSuccess("Quiz uploaded successfully")
                        clearForm()
                    }
                    .addOnFailureListener { e ->
                        binding.progressBar.visibility = View.GONE
                        binding.uploadButton.isEnabled = true
                        showError("Upload failed: ${e.message}")
                    }
            } catch (e: Exception) {
                Timber.e(e, "Quiz upload failed")
                binding.progressBar.visibility = View.GONE
                binding.uploadButton.isEnabled = true
                showError("Upload failed: ${e.message}")
            }
        }
    }

    private fun clearForm() {
        binding.quizTitleInput.text.clear()
        binding.quizDescriptionInput.text.clear()
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showSuccess(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
