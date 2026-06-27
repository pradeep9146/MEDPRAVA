package com.medprava.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.medprava.databinding.ActivityMainBinding
import com.medprava.data.repository.AuthRepository
import com.medprava.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            if (authRepository.isUserLoggedIn()) {
                navigateToHome()
            } else {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        // Navigate to LoginActivity
        val intent = android.content.Intent(this, com.medprava.ui.auth.LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToHome() {
        // Navigate to HomeActivity
        val intent = android.content.Intent(this, com.medprava.ui.home.HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
