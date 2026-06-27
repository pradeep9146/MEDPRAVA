# MedPRAVA - Security and Setup Guide

## Firebase Setup

### 1. Create Firebase Project
1. Visit [Firebase Console](https://console.firebase.google.com)
2. Create a new project named "MedPRAVA"
3. Enable Google Analytics (optional)

### 2. Register Android App
1. Go to Project Settings
2. Click "Add App" and select Android
3. Enter Package Name: `com.medprava`
4. Download `google-services.json`
5. Place it in `app/` directory

### 3. Enable Authentication
1. Go to Authentication > Sign-in method
2. Enable "Email/Password"
3. Set password requirements (min 6 characters)

### 4. Create Firestore Database
1. Go to Firestore Database
2. Create database in "Production mode"
3. Choose region closest to users

### 5. Setup Realtime Database
1. Create Realtime Database
2. Start in test mode (change rules later)

### 6. Setup Storage
1. Create Cloud Storage bucket
2. Set rules for file uploads

### 7. Enable Cloud Messaging
1. Go to Cloud Messaging
2. Copy Server API Key for push notifications

## Firestore Security Rules

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Allow read/write for authenticated users
    match /users/{userId} {
      allow read, write: if request.auth.uid == userId;
    }

    // Allow read for quizzes and notes based on academic year
    match /quizzes/{document=**} {
      allow read: if request.auth != null;
      allow write: if get(/databases/$(database)/documents/users/$(request.auth.uid)).data.isAdmin == true;
    }

    match /notes/{document=**} {
      allow read: if request.auth != null;
      allow write: if get(/databases/$(database)/documents/users/$(request.auth.uid)).data.isAdmin == true;
    }

    // Messages - only between authorized users
    match /messages/{messageId} {
      allow read, write: if request.auth.uid in resource.data.participants;
    }

    // Admin config - only admin access
    match /admin_config/{document=**} {
      allow read, write: if get(/databases/$(database)/documents/users/$(request.auth.uid)).data.isAdmin == true;
    }
  }
}
```

## Environment Configuration

### Development
- Firebase: Use test mode (insecure)
- Logging: Enabled
- Crash reporting: Enabled

### Production
- Firebase: Use production rules
- Logging: Debug logs disabled
- Crash reporting: Enabled
- Code shrinking: Enabled

## Admin Account Setup

1. Create a user account manually
2. Set `isAdmin: true` in Firestore user document
3. Set initial unlock code in `admin_config/settings`:
   ```
   unlock_code: "your_secret_code"
   ```

## Unlock Code Security

- Change the default code immediately
- Use a strong, random code
- Do NOT share the code in code repositories
- Store code only in Firebase
- Log all code changes

## Building and Deployment

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### Requirements for Release
1. Signing key configured
2. ProGuard rules enabled
3. Firebase crash reporting enabled
4. All analytics configured
5. Privacy policy link added

## Data Backup

- Firebase automatically backs up data
- Enable firestore backups for compliance
- Regular manual exports recommended

## User Permissions

- **Students**: Can view quizzes/notes for their year, access unlocked messenger
- **Admins**: Can upload content, manage users, change unlock code
- **Unauthenticated**: Can only view login screen

## Best Practices

1. **Never commit sensitive data** (keys, passwords, codes)
2. **Use environment variables** for configuration
3. **Enable 2FA** on Firebase console access
4. **Review security rules** regularly
5. **Monitor user activity** through analytics
6. **Keep dependencies updated**
7. **Use ProGuard/R8** for code obfuscation
8. **Test on real devices** before release
