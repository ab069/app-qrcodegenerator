# Android App Template

Base template for daily Android app development. Built with Kotlin + Jetpack Compose + Material 3.

## Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose + Material 3
- **Navigation:** Navigation Compose
- **Storage:** Room (opt-in, commented out by default)
- **Min SDK:** 26 (Android 8.0)
- **Target SDK:** 35 (Android 15)

## How to use this template

1. Click **"Use this template"** on GitHub to create a new repo
2. Open in Android Studio
3. Rename the package: `Refactor → Rename` on `com.yourapp.template`
4. Update `app_name` in `res/values/strings.xml`
5. Update `applicationId` in `app/build.gradle.kts`
6. Build your feature in `ui/screens/HomeScreen.kt`
7. Enable Room in `app/build.gradle.kts` if you need local storage

## Project structure

```
app/src/main/java/com/yourapp/template/
├── MainActivity.kt          # Entry point
├── Navigation.kt            # NavHost + screen routes
├── data/
│   └── AppDatabase.kt       # Room DB (commented, opt-in)
└── ui/
    ├── screens/
    │   └── HomeScreen.kt    # Main screen — build here
    └── theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

## Daily workflow

```
Hour 1    — Fork template, rename package, define scope
Hour 2-5  — Build core feature
Hour 5-6  — Polish UI, add icon
Hour 6-7  — Test on emulator
Hour 7-8  — Push to GitHub, build AAB, upload to Play Store
```

## Play Store checklist

- [ ] Rename package (`com.yourapp.appname`)
- [ ] Update `app_name` string
- [ ] Add app icon (replace mipmap folders)
- [ ] Write privacy policy and host it online
- [ ] Take 2-8 screenshots
- [ ] Create feature graphic (1024x500px)
- [ ] Build signed AAB: `./gradlew bundleRelease`
- [ ] Fill Data Safety form in Play Console

## GitHub Actions

Every push to `main` automatically builds a debug APK downloadable from the Actions tab.
