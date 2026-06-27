package com.medprava

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber

/**
 * Application class for MedPRAVA.
 * Initializes Hilt, Firebase, and other dependencies.
 */
@HiltAndroidApp
class MedPRAVAApp : Application() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        // Initialize Timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
