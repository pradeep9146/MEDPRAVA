package com.medprava.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.medprava.R
import com.medprava.data.repository.AuthRepository
import com.medprava.databinding.ActivitySignupBinding
import com.medprava.util.Constants
import com.medprava.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        // Setup Academic Year Spinner
        val academicYearAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Constants.ACADEMIC_YEARS
        )
        academicYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.academicYearSpinner.adapter = academicYearAdapter

        binding.signupButton.setOnClickListener {
            val fullName = binding.fullNameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            val confirmPassword = binding.confirmPasswordInput.text.toString().trim()
            val university = binding.universityInput.text.toString().trim()
            val academicYear = binding.academicYearSpinner.selectedItem.toString()

            if (validateInputs(fullName, email, password, confirmPassword, university)) {
                signup(fullName, email, password, university, academicYear)
            }
        }

        binding.loginLink.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun validateInputs(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String,
        university: String
    ): Boolean {
        if (fullName.isEmpty()) {
            showError("Full name is required")
            return false
        }
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
        if (password != confirmPassword) {
            showError("Passwords do not match")
            return false
        }
        if (university.isEmpty()) {
            showError("University is required")
            return false
        }
        return true
    }

    private fun signup(
        fullName: String,
        email: String,
        password: String,
        university: String,
        academicYear: String
    ) {
        binding.signupButton.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                authRepository.signUp(fullName, email, password, university, academicYear)
                val userId = authRepository.getCurrentUserId()
                if (userId != null) {
                    preferenceManager.saveUserId(userId)
                    preferenceManager.saveUserEmail(email)
                    preferenceManager.saveUserName(fullName)
                    preferenceManager.saveAcademicYear(academicYear)
                    navigateToHome()
                }
            } catch (e: Exception) {
                Timber.e(e, "Sign up failed")
                showError("Sign up failed: ${e.message}")
                binding.signupButton.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(android.content.Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateToHome() {
        startActivity(android.content.Intent(this, com.medprava.ui.home.HomeActivity::class.java))
        finish()
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
