package com.medprava.data.repository

import com.medprava.data.remote.FirebaseService
import com.medprava.domain.model.Notes
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val firebaseService: FirebaseService
) {

    suspend fun getNotes(academicYear: String): List<Notes> {
        return firebaseService.getNotes(academicYear)
    }

    suspend fun getNotesById(notesId: String): Notes? {
        return firebaseService.getNotesById(notesId)
    }
}
