package com.example.pill_glassmorphism.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pill_glassmorphism.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }

}
