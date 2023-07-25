package com.example.loginmvvm.splash.di

import com.example.loginmvvm.splash.data.cache.SplashCache
import com.example.loginmvvm.splash.data.cache.SplashCacheImpl
import com.example.loginmvvm.splash.data.repository.SplashRepository
import com.example.loginmvvm.splash.data.repository.SplashRepositoryImpl
import com.example.loginmvvm.splash.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SplashModule {
    val instance = module {
        single<SplashCache> { SplashCacheImpl(cache = get()) }
        factory<SplashRepository> { SplashRepositoryImpl(splashCache = get()) }
        viewModel { SplashViewModel(repository = get()) }
    }
}
