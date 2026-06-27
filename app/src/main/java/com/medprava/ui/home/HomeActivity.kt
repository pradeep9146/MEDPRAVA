package com.medprava.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.medprava.R
import com.medprava.data.repository.QuizRepository
import com.medprava.data.repository.NotesRepository
import com.medprava.data.repository.AuthRepository
import com.medprava.databinding.ActivityHomeBinding
import com.medprava.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var quizRepository: QuizRepository

    @Inject
    lateinit var notesRepository: NotesRepository

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        loadContent()
    }

    private fun setupUI() {
        val userName = preferenceManager.getUserName() ?: "Student"
        binding.welcomeText.text = "Welcome, $userName"

        binding.searchBar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    handleSearch(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun loadContent() {
        val academicYear = preferenceManager.getAcademicYear() ?: "1st Year"
        binding.academicYearLabel.text = "Year: $academicYear"

        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                val quizzes = quizRepository.getQuizzes(academicYear)
                val notes = notesRepository.getNotes(academicYear)

                binding.progressBar.visibility = View.GONE

                if (quizzes.isNotEmpty()) {
                    binding.quizzesRecyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
                    binding.quizzesRecyclerView.adapter = QuizAdapter(quizzes) { quiz ->
                        navigateToQuiz(quiz.quizId)
                    }
                    binding.quizzesEmptyState.visibility = View.GONE
                } else {
                    binding.quizzesEmptyState.visibility = View.VISIBLE
                }

                if (notes.isNotEmpty()) {
                    binding.notesRecyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
                    binding.notesRecyclerView.adapter = NotesAdapter(notes) { notesItem ->
                        navigateToNotes(notesItem.notesId)
                    }
                    binding.notesEmptyState.visibility = View.GONE
                } else {
                    binding.notesEmptyState.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to load content")
                binding.progressBar.visibility = View.GONE
                showError("Failed to load content: ${e.message}")
            }
        }
    }

    private fun handleSearch(query: String) {
        val trimmedQuery = query.trim()

        // Check for secret unlock code
        lifecycleScope.launch {
            try {
                if (authRepository.isUserLoggedIn()) {
                    val isValidCode = com.medprava.data.repository.MessengerRepository(
                        com.medprava.data.remote.FirebaseService(
                            com.google.firebase.auth.FirebaseAuth.getInstance(),
                            com.google.firebase.firestore.FirebaseFirestore.getInstance()
                        )
                    ).verifyUnlockCode(trimmedQuery)

                    if (isValidCode) {
                        preferenceManager.setMessengerAccess(true)
                        navigateToMessenger()
                    } else {
                        // Normal search - load educational content
                        performEducationalSearch(trimmedQuery)
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Search error")
                performEducationalSearch(trimmedQuery)
            }
        }
    }

    private fun performEducationalSearch(query: String) {
        Snackbar.make(binding.root, "Searching for: $query", Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToQuiz(quizId: String) {
        Snackbar.make(binding.root, "Opening quiz: $quizId", Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToNotes(notesId: String) {
        Snackbar.make(binding.root, "Opening notes: $notesId", Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToMessenger() {
        val intent = android.content.Intent(this, com.medprava.ui.messenger.MessengerActivity::class.java)
        startActivity(intent)
    }

    private fun logout() {
        lifecycleScope.launch {
            authRepository.logout()
            preferenceManager.clearAll()
            startActivity(android.content.Intent(this@HomeActivity, com.medprava.ui.auth.LoginActivity::class.java))
            finish()
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
