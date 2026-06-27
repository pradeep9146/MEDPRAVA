package com.medprava.ui.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.medprava.databinding.ActivityUserManagementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
