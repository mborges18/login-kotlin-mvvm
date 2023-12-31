package com.example.loginmvvm

import android.app.Application
import com.example.loginmvvm.access.signin.di.SignInModule
import com.example.loginmvvm.access.signup.di.SignUpModule
import com.example.loginmvvm.common.data.local.cache.di.CacheModule
import com.example.loginmvvm.home.di.HomeModule
import com.example.loginmvvm.splash.di.SplashModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(
                listOf(
                    CacheModule.instance,
                    SplashModule.instance,
                    SignInModule.instance,
                    SignUpModule.instance,
                    HomeModule.instance
                )
            )
        }
    }
}
