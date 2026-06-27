package com.medprava.domain.model

import com.google.firebase.firestore.PropertyName

data class Notes(
    @PropertyName("notesId")
    val notesId: String = "",

    @PropertyName("title")
    val title: String = "",

    @PropertyName("description")
    val description: String = "",

    @PropertyName("subject")
    val subject: String = "",

    @PropertyName("academicYear")
    val academicYear: String = "",

    @PropertyName("content")
    val content: String = "",

    @PropertyName("documentUrl")
    val documentUrl: String = "",

    @PropertyName("imageUrl")
    val imageUrl: String = "",

    @PropertyName("tags")
    val tags: List<String> = emptyList(),

    @PropertyName("uploadedBy")
    val uploadedBy: String = "",

    @PropertyName("createdAt")
    val createdAt: Long = System.currentTimeMillis(),

    @PropertyName("updatedAt")
    val updatedAt: Long = System.currentTimeMillis(),

    @PropertyName("downloads")
    val downloads: Int = 0,

    @PropertyName("isActive")
    val isActive: Boolean = true
)

data class Subject(
    @PropertyName("subjectId")
    val subjectId: String = "",

    @PropertyName("subjectName")
    val subjectName: String = "",

    @PropertyName("description")
    val description: String = "",

    @PropertyName("academicYear")
    val academicYear: String = "",

    @PropertyName("icon")
    val icon: String = "",

    @PropertyName("color")
    val color: String = "#0066CC"
)
