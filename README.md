# QR Code Generator

Generate QR codes instantly from any URL or text. Free, no ads, no subscriptions, no data collection.

## Features

- Generate QR codes from URLs or plain text
- Live preview — QR updates as you type
- Share QR code via any app (messages, email, WhatsApp, etc.)
- URL / Text type selector
- Runs fully offline — no internet permission

## Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose + Material 3
- **QR Generation:** ZXing Core 3.5.3
- **Architecture:** ViewModel + StateFlow
- **Min SDK:** 26 (Android 8.0)
- **Target SDK:** 35 (Android 15)

## Project structure

```
app/src/main/java/com/ab069/qrcodegenerator/
├── MainActivity.kt
├── QrViewModel.kt        # QR generation logic + UiState
└── ui/
    ├── screens/
    │   └── HomeScreen.kt # Single screen UI
    └── theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

## Build

```bash
./gradlew assembleDebug      # debug APK
./gradlew bundleRelease      # signed AAB for Play Store
```

## Play Store checklist

- [ ] Add app icon (replace mipmap folders)
- [ ] Write privacy policy and host it online
- [ ] Take 2-8 screenshots
- [ ] Create feature graphic (1024x500px)
- [ ] Fill Data Safety form in Play Console
