package com.medprava.ui.admin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.medprava.databinding.ActivityNotesUploadBinding
import com.medprava.domain.model.Notes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject
import com.google.firebase.firestore.FirebaseFirestore

@AndroidEntryPoint
class NotesUploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesUploadBinding
    @Inject
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.uploadButton.setOnClickListener {
            uploadNotes()
        }
    }

    private fun uploadNotes() {
        val title = binding.notesTitleInput.text.toString().trim()
        val description = binding.notesDescriptionInput.text.toString().trim()
        val subject = binding.subjectSpinner.selectedItem.toString()
        val academicYear = binding.academicYearSpinner.selectedItem.toString()

        if (title.isEmpty() || description.isEmpty()) {
            showError("All fields are required")
            return
        }

        binding.uploadButton.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val notesId = UUID.randomUUID().toString()
                val notes = Notes(
                    notesId = notesId,
                    title = title,
                    description = description,
                    subject = subject,
                    academicYear = academicYear,
                    content = description,
                    isActive = true
                )

                firestore.collection("notes").document(notesId).set(notes)
                    .addOnSuccessListener {
                        binding.progressBar.visibility = View.GONE
                        binding.uploadButton.isEnabled = true
                        showSuccess("Notes uploaded successfully")
                        clearForm()
                    }
                    .addOnFailureListener { e ->
                        binding.progressBar.visibility = View.GONE
                        binding.uploadButton.isEnabled = true
                        showError("Upload failed: ${e.message}")
                    }
            } catch (e: Exception) {
                Timber.e(e, "Notes upload failed")
                binding.progressBar.visibility = View.GONE
                binding.uploadButton.isEnabled = true
                showError("Upload failed: ${e.message}")
            }
        }
    }

    private fun clearForm() {
        binding.notesTitleInput.text.clear()
        binding.notesDescriptionInput.text.clear()
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showSuccess(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
