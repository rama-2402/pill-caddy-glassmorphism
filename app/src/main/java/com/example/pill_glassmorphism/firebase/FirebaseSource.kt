package com.example.pill_glassmorphism.firebase

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable

class FirebaseSource {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (!emitter.isDisposed){
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    it.exception?.let { it -> emitter.onError(it) }
            }
        }
    }



    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (!emitter.isDisposed){
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    it.exception?.let { it -> emitter.onError(it) }
            }
        }
    }

    fun signOut() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser

}