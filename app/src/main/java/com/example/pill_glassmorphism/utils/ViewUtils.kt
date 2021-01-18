package com.example.pill_glassmorphism.utils

import android.content.Context
import android.content.Intent
import com.example.pill_glassmorphism.auth.SignInActivity
import com.example.pill_glassmorphism.homepage.HomeActivity

fun Context.startHomeActivity() = Intent(this, HomeActivity::class.java)
    .also {
        it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(it)
    }


fun Context.startSignInActivity() = Intent(this, SignInActivity::class.java).also {
    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(it)
}
