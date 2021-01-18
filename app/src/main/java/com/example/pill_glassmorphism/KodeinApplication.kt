package com.example.pill_glassmorphism

import android.app.Application
import com.example.pill_glassmorphism.auth.AuthViewModelFactory
import com.example.pill_glassmorphism.firebase.FirebaseSource
import com.example.pill_glassmorphism.homepage.HomeViewModelFactory
import com.example.pill_glassmorphism.repositories.UserRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class KodeinApplication: Application(), KodeinAware {
    /**
     * A Kodein Aware class must be within reach of a [Kodein] object.
     */
    override val kodein = Kodein.lazy{
        import(androidXModule(this@KodeinApplication))

        bind() from singleton { FirebaseSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }
}