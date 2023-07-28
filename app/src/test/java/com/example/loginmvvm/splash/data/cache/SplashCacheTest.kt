package com.example.loginmvvm.splash.data.cache

import com.example.loginmvvm.common.data.local.cache.Cache
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class SplashCacheTest {
    private val cache: Cache = mockk(relaxed = true)
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
        coEvery { cache.get("KEEP_LOGGED") } returns isKeepLogged.toString()
        splashCache = SplashCacheImpl(cache)
    }
}
