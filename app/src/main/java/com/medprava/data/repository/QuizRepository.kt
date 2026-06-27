package com.medprava.data.repository

import com.medprava.data.local.dao.QuizDao
import com.medprava.data.remote.FirebaseService
import com.medprava.domain.model.Quiz
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val firebaseService: FirebaseService,
    private val quizDao: QuizDao
) {

    suspend fun getQuizzes(academicYear: String): List<Quiz> {
        return firebaseService.getQuizzes(academicYear)
    }

    suspend fun getQuiz(quizId: String): Quiz? {
        return firebaseService.getQuiz(quizId)
    }

    fun getLocalQuizzes(academicYear: String): Flow<List<Quiz>> {
        return firebaseService.getQuizzes(academicYear) as Flow<List<Quiz>>
    }
}
