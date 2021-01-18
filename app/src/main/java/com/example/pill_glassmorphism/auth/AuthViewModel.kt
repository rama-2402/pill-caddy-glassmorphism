package com.example.pill_glassmorphism.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.pill_glassmorphism.repositories.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel (private val repository: UserRepository): ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    val invalid: String = "Invalid email or password. Check Again!"

    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

//SignIn Authentication

    fun signIn(){
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
        authListener?.onStarted()

        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(invalid)
            })
        disposables.add(disposable)
    }

//SignUp Authentication

    fun signUp(){
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
        authListener?.onStarted()

        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(invalid)
            })
        disposables.add(disposable)
    }

//Move to signUp page from signIn page


    fun gotoSignUp(view: View){
        Intent(view.context,SignUpActivity::class.java).also{
            view.context.startActivity(it)
        }
    }
//Move to signIn page from signUp page

    fun gotoSignIn(view: View){
        authListener?.moveSignIn()
//         Intent(view.context, SignInActivity::class.java)
//            .also {
//            view.context.startActivity(it)
//        }

    }


//clearing out the disposables and freeing the memory

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}




