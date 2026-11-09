# Build your Dayleaf Android app
Requires Android 8 or later and an internet connection. Your laptop continues using the existing Dayleaf link. The Android app uses a separate WebView login session.

## Build using a private GitHub repository on Windows
1. Extract this ZIP on your laptop. Open the extracted folder containing settings.gradle.
2. On GitHub create an empty private repository named dayleaf-android.
3. Choose “uploading an existing file” (or Add file → Upload files). Drag the app folder, build.gradle and settings.gradle into the uploader and commit them to the default branch.
4. Choose Add file → Create new file. Enter .github/workflows/android.yml as the filename. Open the extracted workflow file with Notepad, copy all its contents into the editor, and commit. If .github is hidden in File Explorer, turn on View → Show → Hidden items.
5. Open Actions → Build Dayleaf APK → newest run. If GitHub asks to enable Actions, enable it and use Run workflow. Wait for the green check. If a build fails, the build log gives the failing step.
6. In the run summary, download Dayleaf-Android under Artifacts. Extract Dayleaf.apk from that ZIP and transfer it to your phone.
7. Open Dayleaf.apk. If prompted, allow that specific app (such as Files) to install unknown apps, install Dayleaf, then switch the permission off.
8. Launch Dayleaf from the Android app drawer. Sign in with the same account as your private laptop notebook. Test sync by adding a note on your phone and refreshing the notebook on your laptop.

## Updates and sign-in
Notebook interface updates load from your existing hosted site. Rebuilding the APK is only necessary when the Android wrapper changes. Hosted runners use temporary debug signing keys. A later build may require uninstalling the previous APK first. This clears the app's login and local settings. Verify that your entries have synced before uninstalling.

If your login provider rejects embedded browsers, this WebView version may stop at sign-in. A Chrome login does not transfer to this app. Keep using the existing site for access while a native authentication integration is developed. Never enter account credentials into a custom form.

## Local build
With JDK 17, Gradle 8.9 and Android SDK platform 35 installed, run gradle :app:assembleDebug from this folder. The APK appears at app/build/outputs/apk/debug/app-debug.apk.
