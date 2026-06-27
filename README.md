# MedPRAVA - Medical Education Application

MedPRAVA is a comprehensive medical education application designed specifically for MBBS students. It provides year-wise educational content, quizzes, notes, and a secure hidden messaging system.

## Features

### Core Features
- **Secure Authentication**: Full Name, Email, Password, University, Academic Year
- **Home Dashboard**: Search bar, quizzes, subjects, notes, recent activity, progress statistics
- **Year-wise Content**: Different content for each academic year (1st-5th Year + Internship)
- **Educational Content**: Subjects including Anatomy, Physiology, Pharmacology, etc.

### Hidden Features (Secret Unlock)
- **Messenger Module**: Hidden messaging system unlocked via admin-defined code
  - One-to-one chat
  - Group chat
  - Voice messages
  - Image/Document/Video sharing
  - Voice and video calls
  - Online/offline status
  - Typing indicators
  - Read receipts
  - Chat search

### Admin Panel
- Upload quizzes and notes
- Manage users
- Manage academic years
- Change secret unlock code
- Send announcements
- View app usage analytics

## Tech Stack

- **Language**: Kotlin
- **Android Version**: API 28+
- **Architecture**: MVVM with Clean Architecture
- **Database**: Firebase Firestore + Realtime Database
- **Authentication**: Firebase Authentication
- **UI Framework**: Material Design 3
- **State Management**: LiveData & StateFlow
- **Dependency Injection**: Hilt
- **Image Loading**: Glide
- **Networking**: Retrofit + OkHttp
- **Local Storage**: Room Database
- **Notifications**: Firebase Cloud Messaging

## Project Structure

```
MedPRAVA/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/medprava/
│   │   │   │   ├── ui/
│   │   │   │   │   ├── auth/
│   │   │   │   │   ├── home/
│   │   │   │   │   ├── messenger/
│   │   │   │   │   ├── quiz/
│   │   │   │   │   ├── notes/
│   │   │   │   │   ├── admin/
│   │   │   │   │   └── common/
│   │   │   │   ├── data/
│   │   │   │   │   ├── repository/
│   │   │   │   │   ├── remote/
│   │   │   │   │   ├── local/
│   │   │   │   │   └── model/
│   │   │   │   ├── domain/
│   │   │   │   │   ├── model/
│   │   │   │   │   ├── repository/
│   │   │   │   │   └── usecase/
│   │   │   │   ├── di/
│   │   │   │   ├── util/
│   │   │   │   └── MedPRAVAApp.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   ├── values/
│   │   │   │   └── anim/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Kotlin 1.7+
- JDK 11+
- Firebase project setup

### Installation

1. Clone the repository
```bash
git clone https://github.com/pradeep9146/MedPRAVA.git
cd MedPRAVA
```

2. Setup Firebase
   - Create a Firebase project at https://console.firebase.google.com
   - Download `google-services.json`
   - Place it in `app/` directory

3. Build and run
```bash
./gradlew build
./gradlew installDebug
```

## Configuration

### Admin Code Setup
Set the secret unlock code in Firebase:
```
Firestore: admin_config/unlock_code = "your_secret_code"
```

### Firebase Setup
1. Enable Authentication: Email/Password
2. Create Firestore Database
3. Create Realtime Database
4. Enable Storage
5. Enable Cloud Messaging

## Security

- All authentication via Firebase
- Encrypted local storage for sensitive data
- Server-side verification of unlock code
- User authorization checks for messenger access
- Secure WebSocket for real-time messaging
- End-to-end encryption for messages (planned)

## Build Instructions

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

## License

Copyright © 2024 MedPRAVA. All rights reserved.
