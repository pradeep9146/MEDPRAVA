package com.medprava.data.repository

import com.medprava.data.remote.FirebaseService
import com.medprava.domain.model.Message
import com.medprava.domain.model.ChatRoom
import javax.inject.Inject

class MessengerRepository @Inject constructor(
    private val firebaseService: FirebaseService
) {

    suspend fun sendMessage(message: Message) {
        firebaseService.sendMessage(message)
    }

    suspend fun getMessages(chatRoomId: String): List<Message> {
        return firebaseService.getMessages(chatRoomId)
    }

    suspend fun verifyUnlockCode(code: String): Boolean {
        return firebaseService.verifyUnlockCode(code)
    }
}
