package com.medprava.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.medprava.data.local.dao.QuizDao
import com.medprava.data.local.entity.QuizEntity

@Database(
    entities = [QuizEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MedPRAVADatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var instance: MedPRAVADatabase? = null

        fun getDatabase(context: Context): MedPRAVADatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    MedPRAVADatabase::class.java,
                    "medprava_database"
                ).build()
                instance = db
                db
            }
        }
    }
}
