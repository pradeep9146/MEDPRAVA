package com.medprava.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.medprava.R
import com.medprava.data.repository.AuthRepository
import com.medprava.databinding.ActivityLoginBinding
import com.medprava.util.Constants
import com.medprava.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (validateInputs(email, password)) {
                login(email, password)
            }
        }

        binding.signupLink.setOnClickListener {
            navigateToSignUp()
        }

        binding.forgotPasswordLink.setOnClickListener {
            navigateToForgotPassword()
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            showError("Email is required")
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Invalid email format")
            return false
        }
        if (password.isEmpty()) {
            showError("Password is required")
            return false
        }
        if (password.length < 6) {
            showError("Password must be at least 6 characters")
            return false
        }
        return true
    }

    private fun login(email: String, password: String) {
        binding.loginButton.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                authRepository.login(email, password)
                val userId = authRepository.getCurrentUserId()
                if (userId != null) {
                    preferenceManager.saveUserId(userId)
                    preferenceManager.saveUserEmail(email)
                    navigateToHome()
                }
            } catch (e: Exception) {
                Timber.e(e, "Login failed")
                showError("Login failed: ${e.message}")
                binding.loginButton.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun navigateToSignUp() {
        startActivity(android.content.Intent(this, SignUpActivity::class.java))
        finish()
    }

    private fun navigateToForgotPassword() {
        startActivity(android.content.Intent(this, ForgotPasswordActivity::class.java))
    }

    private fun navigateToHome() {
        startActivity(android.content.Intent(this, com.medprava.ui.home.HomeActivity::class.java))
        finish()
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
