package com.medprava.ui.messenger

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.medprava.R
import com.medprava.data.repository.MessengerRepository
import com.medprava.databinding.ActivityMessengerBinding
import com.medprava.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MessengerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessengerBinding

    @Inject
    lateinit var messengerRepository: MessengerRepository

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessengerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if user has messenger access
        if (!preferenceManager.hasMessengerAccess()) {
            Snackbar.make(binding.root, "Unauthorized access", Snackbar.LENGTH_LONG).show()
            finish()
            return
        }

        setupUI()
        loadChats()
    }

    private fun setupUI() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.newChatButton.setOnClickListener {
            navigateToNewChat()
        }

        binding.tabLayout.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showChats() // One-to-one chats
                    1 -> showGroups() // Group chats
                    2 -> showCalls() // Call history
                }
            }

            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }

    private fun loadChats() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                // Load chats from Firebase
                binding.progressBar.visibility = View.GONE
                binding.emptyState.visibility = View.VISIBLE
            } catch (e: Exception) {
                Timber.e(e, "Failed to load chats")
                binding.progressBar.visibility = View.GONE
                showError("Failed to load chats")
            }
        }
    }

    private fun showChats() {
        binding.emptyState.text = "No one-to-one chats"
        binding.emptyState.visibility = View.VISIBLE
    }

    private fun showGroups() {
        binding.emptyState.text = "No group chats"
        binding.emptyState.visibility = View.VISIBLE
    }

    private fun showCalls() {
        binding.emptyState.text = "No call history"
        binding.emptyState.visibility = View.VISIBLE
    }

    private fun navigateToNewChat() {
        Snackbar.make(binding.root, "New chat - Coming soon", Snackbar.LENGTH_SHORT).show()
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
