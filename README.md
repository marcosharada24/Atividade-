ProfileAppComplete - skeleton Android project (Kotlin)
-----------------------------------------------------
What's included:
- Login screen (saves user in SharedPreferences)
- Profile screen (edit name, email, change photo from gallery using Activity Result API)
- MVVM: UserViewModel + UserRepository
- ViewBinding enabled
- Coil for image loading

Notes:
- This is a ready-to-open Android Studio project, but the gradle wrapper jar is not included in this zip.
  If you open the project in Android Studio it will download required Gradle components automatically.
- You may need to update plugin and Gradle versions in Android Studio to match your environment.
- To test image picking, run on a real device or emulator with images available.

Next steps I can do on request:
- Add Room DB instead of SharedPreferences
- Add change-photo via camera + storage permissions
- Wire a real authentication backend

