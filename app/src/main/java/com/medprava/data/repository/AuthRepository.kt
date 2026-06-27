package com.medprava.data.repository

import com.medprava.data.remote.FirebaseService
import com.medprava.domain.model.User
import com.medprava.domain.model.AcademicYear
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseService: FirebaseService
) {

    suspend fun signUp(
        fullName: String,
        email: String,
        password: String,
        university: String,
        academicYear: String
    ) {
        val user = User(
            fullName = fullName,
            email = email,
            university = university,
            academicYear = academicYear,
            isAdmin = false,
            hasMessengerAccess = false
        )
        firebaseService.signUp(email, password, user)
    }

    suspend fun login(email: String, password: String) {
        firebaseService.login(email, password)
    }

    suspend fun logout() {
        firebaseService.logout()
    }

    suspend fun resetPassword(email: String) {
        firebaseService.resetPassword(email)
    }

    fun getCurrentUserId() = firebaseService.getCurrentUserId()

    fun isUserLoggedIn() = firebaseService.getCurrentUser() != null

    suspend fun getUserProfile(userId: String) = firebaseService.getUserProfile(userId)
}
