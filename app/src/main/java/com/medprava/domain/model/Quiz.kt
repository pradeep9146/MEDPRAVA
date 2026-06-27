package com.medprava.domain.model

import com.google.firebase.firestore.PropertyName

data class Quiz(
    @PropertyName("quizId")
    val quizId: String = "",

    @PropertyName("title")
    val title: String = "",

    @PropertyName("description")
    val description: String = "",

    @PropertyName("subject")
    val subject: String = "",

    @PropertyName("academicYear")
    val academicYear: String = "",

    @PropertyName("questions")
    val questions: List<Question> = emptyList(),

    @PropertyName("totalQuestions")
    val totalQuestions: Int = 0,

    @PropertyName("duration")
    val duration: Int = 30, // in minutes

    @PropertyName("passingScore")
    val passingScore: Int = 60,

    @PropertyName("createdAt")
    val createdAt: Long = System.currentTimeMillis(),

    @PropertyName("updatedAt")
    val updatedAt: Long = System.currentTimeMillis(),

    @PropertyName("isActive")
    val isActive: Boolean = true
)

data class Question(
    @PropertyName("questionId")
    val questionId: String = "",

    @PropertyName("questionText")
    val questionText: String = "",

    @PropertyName("questionType")
    val questionType: String = "MCQ", // MCQ, Short Answer, True/False

    @PropertyName("options")
    val options: List<String> = emptyList(),

    @PropertyName("correctAnswer")
    val correctAnswer: String = "",

    @PropertyName("marks")
    val marks: Int = 1,

    @PropertyName("explanation")
    val explanation: String = ""
)

data class QuizResponse(
    @PropertyName("responseId")
    val responseId: String = "",

    @PropertyName("userId")
    val userId: String = "",

    @PropertyName("quizId")
    val quizId: String = "",

    @PropertyName("answers")
    val answers: Map<String, String> = emptyMap(),

    @PropertyName("score")
    val score: Int = 0,

    @PropertyName("isPassed")
    val isPassed: Boolean = false,

    @PropertyName("startTime")
    val startTime: Long = System.currentTimeMillis(),

    @PropertyName("endTime")
    val endTime: Long = 0,

    @PropertyName("timeTaken")
    val timeTaken: Long = 0 // in seconds
)
