package com.medprava.ui.messenger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.medprava.databinding.ActivityCallBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCallBinding
    private var callType: String = "VOICE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callType = intent.getStringExtra("callType") ?: "VOICE"
        setupUI()
    }

    private fun setupUI() {
        binding.callTypeLabel.text = if (callType == "VIDEO") "Video Call" else "Voice Call"

        binding.endCallButton.setOnClickListener {
            endCall()
        }

        binding.muteButton.setOnClickListener {
            toggleMute()
        }

        if (callType == "VIDEO") {
            binding.cameraButton.setOnClickListener {
                toggleCamera()
            }
        }
    }

    private fun endCall() {
        finish()
    }

    private fun toggleMute() {
        // Toggle microphone
    }

    private fun toggleCamera() {
        // Toggle camera
    }
}
