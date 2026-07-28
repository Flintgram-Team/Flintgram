<p align="center">
  <img src="docs/mintgram-logo.png" width="180" alt="Mintgram logo">
</p>

<h1 align="center">Mintgram</h1>

<p align="center">
  <a href="LICENSE">Licensed under the GNU General Public License v2.0</a>
</p>

<p align="center">
  Experimental third-party Telegram client based on
  <a href="https://github.com/DrKLO/Telegram">official sources</a>.
</p>

<p align="center">
  <a href="https://t.me/mintgram_tg">
    <img src="https://img.shields.io/badge/Channel-Mintgram-3E927A?style=for-the-badge" alt="Mintgram channel">
  </a>
  <a href="https://t.me/mintgram_chat">
    <img src="https://img.shields.io/badge/Chat-Mintgram-3E927A?style=for-the-badge" alt="Mintgram chat">
  </a>
  <a href="../../releases">
    <img src="https://img.shields.io/badge/Download-Releases-3E927A?style=for-the-badge" alt="Download Releases">
  </a>
</p>

## About

Mintgram is an unofficial fork of Telegram for Android with a custom visual style and privacy-focused client features.

## Features

- Mintgram branding
- Mintgram Basic theme
- Mintgram Extended theme
- Hide Read Status
- Keep Deleted Messages
- Free Voice Transcription toggle
- Custom deleted-message label colors

## Compilation Guide

1. Clone the source code.
2. Put your private values into `.env`.
3. Open the project in Android Studio. It should be opened, not imported.
4. Build the app with Android Studio or Gradle.

```bash
./gradlew :TMessagesProj_App:assembleAfatDebug
```

The APK will be generated at:

```text
TMessagesProj_App/build/outputs/apk/afat/debug/app.apk
```

## Private Values

Keep `.env`, `local.properties`, APK files, AAB files, and keystores out of git.

## Thanks To

- [Telegram](https://telegram.org)
- [Telegram Android](https://github.com/DrKLO/Telegram)
- [AyuGram](https://github.com/AyuGram)
- [exteraGram](https://github.com/exteraSquad/exteraGram)
- [Moegram](https://github.com/Moegram/Moegram#-moegram)

## License

Mintgram is distributed under the GNU General Public License v2.0. See [LICENSE](LICENSE).

## Fork Notice

Mintgram is an unofficial fork of Telegram for Android based on the official Telegram Android source code.
