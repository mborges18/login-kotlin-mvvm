package com.example.loginmvvm.splash.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.loginmvvm.access.AccessActivity
import com.example.loginmvvm.databinding.ActivitySplashBinding
import com.example.loginmvvm.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handlerScreens()
    }

    private fun handlerScreens() {
        viewModel.getKeepLogged()

        viewModel.keepLogged.observe(this) {
            Handler(Looper.getMainLooper()).postDelayed({
                if (it) {
                    startActivity(MainActivity.newIntent(this))
                    finish()
                } else {
                    startActivity(AccessActivity.newIntent(this))
                    finish()
                }
            }, TIMER)
        }
    }

    companion object {
        const val TIMER = 4000L
    }
}
