package com.medprava.domain.model

import com.google.firebase.firestore.PropertyName

data class Announcement(
    @PropertyName("announcementId")
    val announcementId: String = "",

    @PropertyName("title")
    val title: String = "",

    @PropertyName("description")
    val description: String = "",

    @PropertyName("content")
    val content: String = "",

    @PropertyName("targetAcademicYears")
    val targetAcademicYears: List<String> = emptyList(),

    @PropertyName("priority")
    val priority: String = "NORMAL", // HIGH, NORMAL, LOW

    @PropertyName("createdBy")
    val createdBy: String = "",

    @PropertyName("createdAt")
    val createdAt: Long = System.currentTimeMillis(),

    @PropertyName("expiresAt")
    val expiresAt: Long = 0,

    @PropertyName("imageUrl")
    val imageUrl: String = "",

    @PropertyName("isActive")
    val isActive: Boolean = true
)
