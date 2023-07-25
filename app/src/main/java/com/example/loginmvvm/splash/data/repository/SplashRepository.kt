package com.example.loginmvvm.splash.data.repository

import com.example.loginmvvm.splash.data.cache.SplashCache

class SplashRepositoryImpl(
    private val splashCache: SplashCache
): SplashRepository {
    override suspend fun getKeepLogged() = splashCache.getKeepLogged()
}

interface SplashRepository {
    suspend fun getKeepLogged(): Boolean
}
