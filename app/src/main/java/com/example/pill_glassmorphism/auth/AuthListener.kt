package com.example.pill_glassmorphism.auth

interface AuthListener {
    fun onSuccess()
    fun onStarted()
    fun onFailure(message: String)
    fun moveSignIn()
}