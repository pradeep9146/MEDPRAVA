package com.medprava.di

import com.medprava.data.repository.AuthRepository
import com.medprava.data.repository.QuizRepository
import com.medprava.data.repository.NotesRepository
import com.medprava.data.repository.MessengerRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule
