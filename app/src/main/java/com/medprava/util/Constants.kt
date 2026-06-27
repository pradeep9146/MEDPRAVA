package com.medprava.util

object Constants {
    // Collections
    const val USERS_COLLECTION = "users"
    const val QUIZZES_COLLECTION = "quizzes"
    const val NOTES_COLLECTION = "notes"
    const val MESSAGES_COLLECTION = "messages"
    const val CHAT_ROOMS_COLLECTION = "chatRooms"
    const val ANNOUNCEMENTS_COLLECTION = "announcements"
    const val ADMIN_CONFIG_COLLECTION = "admin_config"

    // Shared Preferences
    const val PREF_NAME = "medprava_prefs"
    const val KEY_USER_ID = "user_id"
    const val KEY_USER_EMAIL = "user_email"
    const val KEY_USER_NAME = "user_name"
    const val KEY_ACADEMIC_YEAR = "academic_year"
    const val KEY_MESSENGER_ACCESS = "messenger_access"
    const val KEY_THEME_MODE = "theme_mode"

    // Message Types
    const val MESSAGE_TYPE_TEXT = "TEXT"
    const val MESSAGE_TYPE_IMAGE = "IMAGE"
    const val MESSAGE_TYPE_VIDEO = "VIDEO"
    const val MESSAGE_TYPE_DOCUMENT = "DOCUMENT"
    const val MESSAGE_TYPE_VOICE = "VOICE"

    // Quiz Configuration
    const val DEFAULT_QUIZ_DURATION = 30
    const val DEFAULT_PASSING_SCORE = 60

    // Call Types
    const val CALL_TYPE_VOICE = "VOICE"
    const val CALL_TYPE_VIDEO = "VIDEO"

    // Call Status
    const val CALL_STATUS_COMPLETED = "COMPLETED"
    const val CALL_STATUS_MISSED = "MISSED"
    const val CALL_STATUS_DECLINED = "DECLINED"

    // Subjects
    val MEDICAL_SUBJECTS = listOf(
        "Anatomy",
        "Physiology",
        "Pharmacology",
        "Pathology",
        "Biochemistry",
        "Microbiology",
        "Forensic Medicine",
        "Community Medicine",
        "Pediatrics",
        "Obstetrics & Gynaecology"
    )

    // Academic Years
    val ACADEMIC_YEARS = listOf(
        "1st Year",
        "2nd Year",
        "3rd Year",
        "4th Year",
        "5th Year",
        "Internship"
    )
}
