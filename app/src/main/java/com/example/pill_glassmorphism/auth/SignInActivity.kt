package com.example.pill_glassmorphism.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
//import com.daimajia.androidanimations.library.Techniques
//import com.daimajia.androidanimations.library.YoYo
import com.example.pill_glassmorphism.R
import com.example.pill_glassmorphism.databinding.ActivitySignInBinding
import com.example.pill_glassmorphism.utils.startHomeActivity
import io.alterac.blurkit.BlurLayout
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignInActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()

    private lateinit var viewModel: AuthViewModel

//    private lateinit var blur: BlurLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignInBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.authListener = this

//        blur = findViewById(R.id.blur_sign_in)

//        YoYo.with(Techniques.BounceInRight).duration(1500).playOn(login_layout)


    }

    override fun moveSignIn() {

    }

    override fun onSuccess() {
//        progressbar.visibility = View.GONE
        startHomeActivity()
    }

    override fun onStarted() {
//        progressbar.visibility = View.VISIBLE
    }

    override fun onFailure(message: String) {
//        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
//        blur.startBlur()
        viewModel.user?.let {
            startHomeActivity()
        }
    }
}
