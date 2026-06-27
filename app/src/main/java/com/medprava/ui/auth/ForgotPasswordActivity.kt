package com.medprava.ui.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.medprava.data.repository.AuthRepository
import com.medprava.databinding.ActivityForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.resetButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            if (validateEmail(email)) {
                resetPassword(email)
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        if (email.isEmpty()) {
            showError("Email is required")
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Invalid email format")
            return false
        }
        return true
    }

    private fun resetPassword(email: String) {
        binding.resetButton.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                authRepository.resetPassword(email)
                showSuccess("Password reset email sent to $email")
                finish()
            } catch (e: Exception) {
                Timber.e(e, "Password reset failed")
                showError("Password reset failed: ${e.message}")
                binding.resetButton.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showSuccess(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
