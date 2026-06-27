package com.medprava.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.PREF_NAME,
        Context.MODE_PRIVATE
    )

    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString(Constants.KEY_USER_ID, userId).apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(Constants.KEY_USER_ID, null)
    }

    fun saveUserEmail(email: String) {
        sharedPreferences.edit().putString(Constants.KEY_USER_EMAIL, email).apply()
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(Constants.KEY_USER_EMAIL, null)
    }

    fun saveUserName(name: String) {
        sharedPreferences.edit().putString(Constants.KEY_USER_NAME, name).apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString(Constants.KEY_USER_NAME, null)
    }

    fun saveAcademicYear(year: String) {
        sharedPreferences.edit().putString(Constants.KEY_ACADEMIC_YEAR, year).apply()
    }

    fun getAcademicYear(): String? {
        return sharedPreferences.getString(Constants.KEY_ACADEMIC_YEAR, null)
    }

    fun setMessengerAccess(hasAccess: Boolean) {
        sharedPreferences.edit().putBoolean(Constants.KEY_MESSENGER_ACCESS, hasAccess).apply()
    }

    fun hasMessengerAccess(): Boolean {
        return sharedPreferences.getBoolean(Constants.KEY_MESSENGER_ACCESS, false)
    }

    fun setThemeMode(isDarkMode: Boolean) {
        sharedPreferences.edit().putBoolean(Constants.KEY_THEME_MODE, isDarkMode).apply()
    }

    fun isDarkMode(): Boolean {
        return sharedPreferences.getBoolean(Constants.KEY_THEME_MODE, false)
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}
