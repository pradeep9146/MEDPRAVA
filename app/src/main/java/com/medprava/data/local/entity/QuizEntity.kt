package com.medprava.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quizzes")
data class QuizEntity(
    @PrimaryKey
    @ColumnInfo(name = "quizId")
    val quizId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "subject")
    val subject: String,

    @ColumnInfo(name = "academicYear")
    val academicYear: String,

    @ColumnInfo(name = "totalQuestions")
    val totalQuestions: Int,

    @ColumnInfo(name = "duration")
    val duration: Int,

    @ColumnInfo(name = "passingScore")
    val passingScore: Int,

    @ColumnInfo(name = "createdAt")
    val createdAt: Long,

    @ColumnInfo(name = "isActive")
    val isActive: Boolean
)
