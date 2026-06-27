package com.medprava.ui.messenger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.medprava.databinding.ActivityChatDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.videoCallButton.setOnClickListener {
            initiateVideoCall()
        }

        binding.voiceCallButton.setOnClickListener {
            initiateVoiceCall()
        }

        binding.sendButton.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val message = binding.messageInput.text.toString().trim()
        if (message.isNotEmpty()) {
            binding.messageInput.text.clear()
        }
    }

    private fun initiateVoiceCall() {
        val intent = android.content.Intent(this, CallActivity::class.java)
        intent.putExtra("callType", "VOICE")
        startActivity(intent)
    }

    private fun initiateVideoCall() {
        val intent = android.content.Intent(this, CallActivity::class.java)
        intent.putExtra("callType", "VIDEO")
        startActivity(intent)
    }
}
