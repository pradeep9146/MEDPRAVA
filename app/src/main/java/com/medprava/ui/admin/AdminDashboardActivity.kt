package com.medprava.ui.admin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.medprava.R
import com.medprava.data.repository.AuthRepository
import com.medprava.databinding.ActivityAdminDashboardBinding
import com.medprava.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashboardBinding

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        verifyAdminAccess()
        setupUI()
    }

    private fun verifyAdminAccess() {
        lifecycleScope.launch {
            try {
                val userId = authRepository.getCurrentUserId()
                if (userId != null) {
                    val user = authRepository.getUserProfile(userId)
                    if (user == null || !user.isAdmin) {
                        showError("Admin access required")
                        finish()
                        return@launch
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Admin verification failed")
                finish()
            }
        }
    }

    private fun setupUI() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.uploadQuizButton.setOnClickListener {
            navigateToQuizUpload()
        }

        binding.uploadNotesButton.setOnClickListener {
            navigateToNotesUpload()
        }

        binding.manageUsersButton.setOnClickListener {
            navigateToUserManagement()
        }

        binding.changeUnlockCodeButton.setOnClickListener {
            showChangeUnlockCodeDialog()
        }

        binding.sendAnnouncementButton.setOnClickListener {
            navigateToAnnouncements()
        }

        binding.viewAnalyticsButton.setOnClickListener {
            showAnalytics()
        }
    }

    private fun navigateToQuizUpload() {
        startActivity(android.content.Intent(this, QuizUploadActivity::class.java))
    }

    private fun navigateToNotesUpload() {
        startActivity(android.content.Intent(this, NotesUploadActivity::class.java))
    }

    private fun navigateToUserManagement() {
        startActivity(android.content.Intent(this, UserManagementActivity::class.java))
    }

    private fun showChangeUnlockCodeDialog() {
        val editText = android.widget.EditText(this)
        editText.hint = "Enter new unlock code"
        editText.inputType = android.text.InputType.TYPE_CLASS_TEXT

        android.app.AlertDialog.Builder(this)
            .setTitle("Change Unlock Code")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val newCode = editText.text.toString().trim()
                if (newCode.isNotEmpty()) {
                    updateUnlockCode(newCode)
                } else {
                    showError("Code cannot be empty")
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun updateUnlockCode(code: String) {
        lifecycleScope.launch {
            try {
                // Update in Firebase
                com.google.firebase.firestore.FirebaseFirestore.getInstance()
                    .collection("admin_config")
                    .document("settings")
                    .update("unlock_code", code)
                    .addOnSuccessListener {
                        showSuccess("Unlock code updated")
                    }
                    .addOnFailureListener { e ->
                        showError("Failed to update code: ${e.message}")
                    }
            } catch (e: Exception) {
                Timber.e(e, "Update unlock code failed")
                showError("Failed to update code")
            }
        }
    }

    private fun navigateToAnnouncements() {
        Snackbar.make(binding.root, "Announcements - Coming soon", Snackbar.LENGTH_SHORT).show()
    }

    private fun showAnalytics() {
        Snackbar.make(binding.root, "Analytics - Coming soon", Snackbar.LENGTH_SHORT).show()
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showSuccess(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
