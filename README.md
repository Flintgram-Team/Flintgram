<p align="center">
  <img src="docs/flintgram-logo.png" width="180" alt="Flintgram logo">
</p>

<h1 align="center">Flintgram</h1>

<p align="center">
  <a href="LICENSE">Licensed under the GNU General Public License v2.0</a>
</p>

<p align="center">
  Experimental third-party Telegram client based on
  <a href="https://github.com/DrKLO/Telegram">official sources</a>.
</p>

<p align="center">
  <a href="https://t.me/flintgram_tg">
    <img src="https://img.shields.io/badge/Channel-Flintgram-3E927A?style=for-the-badge" alt="Flintgram channel">
  </a>
  <a href="https://t.me/flintgram_chat">
    <img src="https://img.shields.io/badge/Chat-Flintgram-3E927A?style=for-the-badge" alt="Flintgram chat">
  </a>
  <a href="../../releases">
    <img src="https://img.shields.io/badge/Download-Releases-3E927A?style=for-the-badge" alt="Download Releases">
  </a>
</p>

## About

Flintgram is an unofficial fork of Telegram for Android with a custom visual style and privacy-focused client features.

## Features

- Flintgram branding
- Flintgram Basic theme
- Flintgram Extended theme
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

Flintgram is distributed under the GNU General Public License v2.0. See [LICENSE](LICENSE).

## Fork Notice

Flintgram is an unofficial fork of Telegram for Android based on the official Telegram Android source code.
