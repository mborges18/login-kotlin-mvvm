package com.example.loginmvvm.common.data.local.cache.di

import com.example.loginmvvm.common.data.local.cache.Cache
import com.example.loginmvvm.common.data.local.cache.CacheImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object CacheModule {
    val instance = module {
        single<Cache> { CacheImpl(androidContext()) }
    }
}