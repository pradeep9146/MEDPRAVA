package com.medprava.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.medprava.domain.model.User
import com.medprava.domain.model.Quiz
import com.medprava.domain.model.Notes
import com.medprava.domain.model.Message
import com.medprava.domain.model.ChatRoom
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseService @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    // Auth Methods
    suspend fun signUp(email: String, password: String, user: User) {
        val authResult = auth.createUserWithEmailAndPassword(email, password).await()
        val firebaseUser = authResult.user
        firebaseUser?.let {
            val profileUpdate = UserProfileChangeRequest.Builder()
                .setDisplayName(user.fullName)
                .build()
            it.updateProfile(profileUpdate).await()
            firestore.collection("users").document(it.uid).set(user.copy(uid = it.uid)).await()
        }
    }

    suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun logout() {
        auth.signOut()
    }

    suspend fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    fun getCurrentUser() = auth.currentUser

    fun getCurrentUserId() = auth.currentUser?.uid

    // User Methods
    suspend fun updateUserProfile(userId: String, user: User) {
        firestore.collection("users").document(userId).set(user).await()
    }

    suspend fun getUserProfile(userId: String): User? {
        return firestore.collection("users").document(userId).get().await().toObject(User::class.java)
    }

    // Quiz Methods
    suspend fun getQuizzes(academicYear: String): List<Quiz> {
        return firestore.collection("quizzes")
            .whereEqualTo("academicYear", academicYear)
            .whereEqualTo("isActive", true)
            .get()
            .await()
            .toObjects(Quiz::class.java)
    }

    suspend fun getQuiz(quizId: String): Quiz? {
        return firestore.collection("quizzes").document(quizId).get().await().toObject(Quiz::class.java)
    }

    // Notes Methods
    suspend fun getNotes(academicYear: String): List<Notes> {
        return firestore.collection("notes")
            .whereEqualTo("academicYear", academicYear)
            .whereEqualTo("isActive", true)
            .get()
            .await()
            .toObjects(Notes::class.java)
    }

    suspend fun getNotesById(notesId: String): Notes? {
        return firestore.collection("notes").document(notesId).get().await().toObject(Notes::class.java)
    }

    // Message Methods
    suspend fun sendMessage(message: Message) {
        firestore.collection("messages").document(message.messageId).set(message).await()
    }

    suspend fun getMessages(chatRoomId: String): List<Message> {
        return firestore.collection("messages")
            .whereEqualTo("chatRoomId", chatRoomId)
            .orderBy("timestamp")
            .get()
            .await()
            .toObjects(Message::class.java)
    }

    // Unlock Code Verification
    suspend fun verifyUnlockCode(code: String): Boolean {
        return try {
            val config = firestore.collection("admin_config").document("settings").get().await()
            val savedCode = config.getString("unlock_code") ?: ""
            savedCode == code && savedCode.isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }
}
