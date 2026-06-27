package com.medprava.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.medprava.data.local.entity.QuizEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Insert
    suspend fun insertQuiz(quiz: QuizEntity)

    @Insert
    suspend fun insertQuizzes(quizzes: List<QuizEntity>)

    @Update
    suspend fun updateQuiz(quiz: QuizEntity)

    @Delete
    suspend fun deleteQuiz(quiz: QuizEntity)

    @Query("SELECT * FROM quizzes WHERE quizId = :quizId")
    suspend fun getQuizById(quizId: String): QuizEntity?

    @Query("SELECT * FROM quizzes WHERE academicYear = :year ORDER BY createdAt DESC")
    fun getQuizzesByYear(year: String): Flow<List<QuizEntity>>

    @Query("SELECT * FROM quizzes WHERE subject = :subject AND academicYear = :year")
    fun getQuizzesBySubjectAndYear(subject: String, year: String): Flow<List<QuizEntity>>

    @Query("SELECT * FROM quizzes ORDER BY createdAt DESC")
    fun getAllQuizzes(): Flow<List<QuizEntity>>

    @Query("DELETE FROM quizzes")
    suspend fun clearAllQuizzes()
}
