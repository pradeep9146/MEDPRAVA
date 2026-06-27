package com.medprava.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MedPRAVAMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("From: ${remoteMessage.from}")

        // Check if message contains a notification payload
        remoteMessage.notification?.let {
            Timber.d("Message Notification Body: ${it.body}")
            showNotification(it.title ?: "MedPRAVA", it.body ?: "")
        }
    }

    override fun onNewToken(token: String) {
        Timber.d("Refreshed token: $token")
        // Send this token to your server or save it for later use
    }

    private fun showNotification(title: String, message: String) {
        val notificationManager = getSystemService(android.content.Context.NOTIFICATION_SERVICE)
            as android.app.NotificationManager

        // Create notification channel for Android 8+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                "medprava_channel",
                "MedPRAVA Notifications",
                android.app.NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = androidx.core.app.NotificationCompat.Builder(this, "medprava_channel")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .build()

        notificationManager.notify((System.currentTimeMillis() / 1000).toInt(), notification)
    }
}
