package com.medprava.domain.model

import com.google.firebase.firestore.PropertyName

data class User(
    @PropertyName("uid")
    val uid: String = "",

    @PropertyName("fullName")
    val fullName: String = "",

    @PropertyName("email")
    val email: String = "",

    @PropertyName("university")
    val university: String = "",

    @PropertyName("academicYear")
    val academicYear: String = "",

    @PropertyName("profileImage")
    val profileImage: String = "",

    @PropertyName("isAdmin")
    val isAdmin: Boolean = false,

    @PropertyName("hasMessengerAccess")
    val hasMessengerAccess: Boolean = false,

    @PropertyName("createdAt")
    val createdAt: Long = System.currentTimeMillis(),

    @PropertyName("updatedAt")
    val updatedAt: Long = System.currentTimeMillis(),

    @PropertyName("isActive")
    val isActive: Boolean = true
)

enum class AcademicYear(val displayName: String) {
    FIRST_YEAR("1st Year"),
    SECOND_YEAR("2nd Year"),
    THIRD_YEAR("3rd Year"),
    FOURTH_YEAR("4th Year"),
    FIFTH_YEAR("5th Year"),
    INTERNSHIP("Internship")
}
