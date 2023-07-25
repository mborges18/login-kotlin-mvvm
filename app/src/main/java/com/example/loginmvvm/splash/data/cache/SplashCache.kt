package com.example.loginmvvm.splash.data.cache

import com.example.loginmvvm.common.data.local.cache.Cache
import com.example.loginmvvm.common.data.local.cache.CacheImpl.Companion.USER_KEEP_LOGGED

class SplashCacheImpl(
    private val cache: Cache
): SplashCache {
    override suspend fun getKeepLogged(): Boolean {
       return cache.get(USER_KEEP_LOGGED).toBoolean()
    }
}

interface SplashCache {
    suspend fun getKeepLogged(): Boolean
}
