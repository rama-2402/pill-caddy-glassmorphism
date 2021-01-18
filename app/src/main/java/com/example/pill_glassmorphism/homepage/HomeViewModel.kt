package com.example.pill_glassmorphism.homepage

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.pill_glassmorphism.repositories.UserRepository
import com.example.pill_glassmorphism.utils.startSignInActivity

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {


    val user by lazy {
        repository.currentUser()
    }

    fun signout(view: View){
        repository.signOut()
        view.context.startSignInActivity()
    }
}