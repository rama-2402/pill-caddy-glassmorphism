package com.example.pill_glassmorphism.homepage

import android.view.View
import androidx.lifecycle.ViewModel
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.MultipleDaysPickCallback
import com.aminography.primedatepicker.picker.theme.DarkThemeFactory
import com.example.pill_glassmorphism.auth.AuthListener
import com.example.pill_glassmorphism.repositories.UserRepository
import com.example.pill_glassmorphism.utils.startSignInActivity

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var homeListener: HomeListener? = null

    val user by lazy {
        repository.currentUser()
    }

    fun signout(view: View) {
        repository.signOut()
        view.context.startSignInActivity()
    }

    fun singlePicker(view: View){
        homeListener?.singleDay()
    }
}
