package com.example.loginmvvm.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.loginmvvm.access.AccessActivity
import com.example.loginmvvm.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handlerScreens()
    }

    private fun handlerScreens() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(AccessActivity.newIntent(this))
        }, TIMER)
    }

    companion object {
        const val TIMER = 4000L
    }
}
