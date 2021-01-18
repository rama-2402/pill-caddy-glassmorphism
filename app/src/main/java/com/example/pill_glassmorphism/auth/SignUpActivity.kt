package com.example.pill_glassmorphism.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
//import com.daimajia.androidanimations.library.Techniques
//import com.daimajia.androidanimations.library.YoYo
import com.example.pill_glassmorphism.R
import com.example.pill_glassmorphism.databinding.ActivitySignUpBinding
import com.example.pill_glassmorphism.utils.startHomeActivity
import io.alterac.blurkit.BlurLayout
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), KodeinAware, AuthListener {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel
//    private lateinit var blur: BlurLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

//            blur = findViewById(R.id.blur_sign_up)

//        YoYo.with(Techniques.BounceInRight).duration(1500).playOn(sign_in_card)

    }

    override fun moveSignIn(){
        startActivity(Intent(this,SignInActivity::class.java))
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }

    override fun onSuccess() {
//        progressbar1.visibility = View.GONE
        startHomeActivity()
    }

    override fun onStarted() {
//        progressbar1.visibility = View.VISIBLE
    }

    override fun onFailure(message: String) {
//        progressbar1.visibility = View.GONE
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