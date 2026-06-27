package com.medprava.domain.model

import com.google.firebase.firestore.PropertyName

data class Message(
    @PropertyName("messageId")
    val messageId: String = "",

    @PropertyName("senderId")
    val senderId: String = "",

    @PropertyName("senderName")
    val senderName: String = "",

    @PropertyName("senderImage")
    val senderImage: String = "",

    @PropertyName("receiverId")
    val receiverId: String = "",

    @PropertyName("chatRoomId")
    val chatRoomId: String = "",

    @PropertyName("messageText")
    val messageText: String = "",

    @PropertyName("messageType")
    val messageType: String = "TEXT", // TEXT, IMAGE, VIDEO, DOCUMENT, VOICE

    @PropertyName("mediaUrl")
    val mediaUrl: String = "",

    @PropertyName("mediaDuration")
    val mediaDuration: Long = 0, // for voice/video

    @PropertyName("timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @PropertyName("isRead")
    val isRead: Boolean = false,

    @PropertyName("readAt")
    val readAt: Long = 0,

    @PropertyName("isDelivered")
    val isDelivered: Boolean = false
)

data class ChatRoom(
    @PropertyName("chatRoomId")
    val chatRoomId: String = "",

    @PropertyName("participants")
    val participants: List<String> = emptyList(),

    @PropertyName("lastMessage")
    val lastMessage: String = "",

    @PropertyName("lastMessageTime")
    val lastMessageTime: Long = System.currentTimeMillis(),

    @PropertyName("lastMessageSenderId")
    val lastMessageSenderId: String = "",

    @PropertyName("unreadCount")
    val unreadCount: Int = 0,

    @PropertyName("isGroup")
    val isGroup: Boolean = false,

    @PropertyName("groupName")
    val groupName: String = "",

    @PropertyName("groupImage")
    val groupImage: String = "",

    @PropertyName("groupAdmin")
    val groupAdmin: String = "",

    @PropertyName("createdAt")
    val createdAt: Long = System.currentTimeMillis()
)

data class UserStatus(
    @PropertyName("userId")
    val userId: String = "",

    @PropertyName("isOnline")
    val isOnline: Boolean = false,

    @PropertyName("lastSeen")
    val lastSeen: Long = System.currentTimeMillis(),

    @PropertyName("isTyping")
    val isTyping: Boolean = false,

    @PropertyName("typingIn")
    val typingIn: String = ""
)
