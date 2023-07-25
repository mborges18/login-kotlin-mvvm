package com.example.loginmvvm.splash.data.cache

import com.example.loginmvvm.common.data.local.cache.Cache
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class SplashCacheTest {
    private lateinit var cache: Cache
    private lateinit var splashCache: SplashCache

    @Test
    fun `given user is keepLogged then return cache result`() = runTest {
        startCache(isKeepLogged = true)
        val keepLogged = splashCache.getKeepLogged()
        Assert.assertEquals(true, keepLogged)
    }

    @Test
    fun `given user is not keepLogged then return cache result`() = runTest {
        startCache(isKeepLogged = false)
        val keepLogged = splashCache.getKeepLogged()
        Assert.assertEquals(false, keepLogged)
    }

    private fun startCache(isKeepLogged: Boolean) {
        cache = object : Cache {
            override suspend fun insert(key: String, value: String) = Unit
            override suspend fun update(key: String, value: String)  = Unit
            override suspend fun delete(key: String)  = Unit
            override suspend fun get(key: String) = isKeepLogged.toString()
        }
        splashCache = SplashCacheImpl(cache)
    }
}
