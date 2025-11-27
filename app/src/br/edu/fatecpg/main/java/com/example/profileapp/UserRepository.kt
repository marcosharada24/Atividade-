package com.example.profileapp

import android.content.Context
import android.content.SharedPreferences

class UserRepository(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUser(user: User) {
        prefs.edit().putString("name", user.name)
            .putString("email", user.email)
            .putString("photoUri", user.photoUri)
            .putBoolean("logged", true)
            .apply()
    }

    fun getUser(): User {
        val name = prefs.getString("name", "") ?: ""
        val email = prefs.getString("email", "") ?: ""
        val photo = prefs.getString("photoUri", null)
        return User(name, email, photo)
    }

    fun isLogged(): Boolean = prefs.getBoolean("logged", false)

    fun logout() { prefs.edit().clear().apply() }
}
