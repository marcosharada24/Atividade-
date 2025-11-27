package com.example.profileapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val email: String,
    val photoUri: String? = null
) : Parcelable
