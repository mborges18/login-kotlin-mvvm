package com.example.loginmvvm.splash

import com.example.loginmvvm.splash.data.repository.SplashRepository
import com.example.loginmvvm.splash.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object SplashModule {

    private lateinit var splashRepository: SplashRepository

    fun getModuleSplashKeepLogged(timeDelay: Long? = null): Module {
        splashRepository = object : SplashRepository {
            override suspend fun getKeepLogged() = true
        }

        return module {
            viewModel { SplashViewModel(splashRepository, timeDelay) }
        }
    }

    fun getModuleSplashNotKeepLogged(timeDelay: Long? = null): Module {
        splashRepository = object : SplashRepository {
            override suspend fun getKeepLogged() = false
        }

        return module {
            viewModel { SplashViewModel(splashRepository, timeDelay) }
        }
    }
}