package com.example.pill_glassmorphism.repositories

import com.example.pill_glassmorphism.firebase.FirebaseSource


class UserRepository (private val firebase: FirebaseSource) {

    fun login (email: String, password: String) = firebase.login(email,password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun signOut() = firebase.signOut()

    fun currentUser() = firebase.currentUser()

}