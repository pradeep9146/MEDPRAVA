package com.medprava.di

import android.content.Context
import androidx.room.Room
import com.medprava.data.local.database.MedPRAVADatabase
import com.medprava.data.local.dao.QuizDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMedPRAVADatabase(
        @ApplicationContext context: Context
    ): MedPRAVADatabase {
        return Room.databaseBuilder(
            context,
            MedPRAVADatabase::class.java,
            "medprava_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideQuizDao(database: MedPRAVADatabase): QuizDao {
        return database.quizDao()
    }
}
