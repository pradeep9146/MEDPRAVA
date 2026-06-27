# MedPRAVA - Installation and Build Guide

## System Requirements

- Android Studio: Giraffe (2022.3.1) or later
- JDK: 11 or later
- SDK: API 28 - 34
- Gradle: 8.0 or later
- Kotlin: 1.9.10

## Step 1: Clone Repository

```bash
git clone https://github.com/pradeep9146/MedPRAVA.git
cd MedPRAVA
```

## Step 2: Firebase Setup

1. Create Firebase project at https://console.firebase.google.com
2. Add Android app (Package: `com.medprava`)
3. Download `google-services.json`
4. Place in `app/` directory

## Step 3: Build Configuration

1. Open project in Android Studio
2. Wait for Gradle sync to complete
3. Update Gradle wrapper if prompted

## Step 4: Build APK

### Debug Build
```bash
./gradlew assembleDebug
```
APK location: `app/build/outputs/apk/debug/app-debug.apk`

### Release Build
```bash
./gradlew assembleRelease
```
APK location: `app/build/outputs/apk/release/app-release.apk`

## Step 5: Install on Device

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Troubleshooting

### Gradle Sync Failed
- Clear `.gradle` folder
- Invalidate cache in Android Studio
- Restart Android Studio

### Compilation Errors
- Update all dependencies
- Check Java version (should be 11+)
- Clear build folder

### Firebase Issues
- Verify `google-services.json` exists
- Check package name matches
- Verify Firebase project ID

### Runtime Issues
- Check minSdkVersion (28+)
- Verify permissions in AndroidManifest.xml
- Check internet connection

## Project Structure

```
app/
├── src/main/
│   ├── java/com/medprava/
│   │   ├── ui/              # UI components
│   │   ├── data/            # Data layer
│   │   ├── domain/          # Domain models
│   │   ├── di/              # Dependency injection
│   │   ├── util/            # Utilities
│   │   ├── service/         # Background services
│   │   └── MedPRAVAApp.kt   # Application class
│   ├── res/                 # Resources
│   └── AndroidManifest.xml
├── build.gradle.kts         # Module build config
└── proguard-rules.pro       # ProGuard rules
```

## Key Dependencies

- **Firebase**: Auth, Firestore, Storage, Messaging
- **Hilt**: Dependency injection
- **Retrofit**: REST API calls
- **Room**: Local database
- **Glide**: Image loading
- **Material Design 3**: UI components

## Run Tests

```bash
./gradlew test
./gradlew connectedAndroidTest
```

## Generate Documentation

```bash
./gradlew dokka
```

## Performance Profiling

- Use Android Profiler in Android Studio
- Monitor CPU, Memory, Network, Battery
- Check for memory leaks
- Profile network requests

## CI/CD Setup

- GitHub Actions configuration for automated builds
- Firebase Test Lab integration
- Automated APK upload to Firebase App Distribution
