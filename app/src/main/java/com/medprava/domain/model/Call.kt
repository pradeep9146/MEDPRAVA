package com.medprava.domain.model

import com.google.firebase.firestore.PropertyName

data class CallLog(
    @PropertyName("callId")
    val callId: String = "",

    @PropertyName("callerId")
    val callerId: String = "",

    @PropertyName("callerName")
    val callerName: String = "",

    @PropertyName("callerImage")
    val callerImage: String = "",

    @PropertyName("receiverId")
    val receiverId: String = "",

    @PropertyName("receiverName")
    val receiverName: String = "",

    @PropertyName("receiverImage")
    val receiverImage: String = "",

    @PropertyName("callType")
    val callType: String = "VOICE", // VOICE or VIDEO

    @PropertyName("duration")
    val duration: Long = 0, // in seconds

    @PropertyName("status")
    val status: String = "COMPLETED", // COMPLETED, MISSED, DECLINED

    @PropertyName("timestamp")
    val timestamp: Long = System.currentTimeMillis()
)
