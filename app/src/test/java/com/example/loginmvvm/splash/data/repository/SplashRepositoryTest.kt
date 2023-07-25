package com.example.loginmvvm.splash.data.repository

import com.example.loginmvvm.splash.data.cache.SplashCache
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class SplashRepositoryTest {
    private val cache: SplashCache = mockk(relaxed = true)
    private val repository: SplashRepository = SplashRepositoryImpl(cache)

    @Test
    fun `given repository is call then verify cache is call`() = runTest {
        coEvery { cache.getKeepLogged() } returns true

        repository.getKeepLogged()

        coVerify { cache.getKeepLogged() }
    }

    @Test
    fun `given user is keepLogged then return cache result`() = runTest {
        coEvery { cache.getKeepLogged() } returns true

        val keepLogged = repository.getKeepLogged()

        Assert.assertEquals(true, keepLogged)
    }

    @Test
    fun `given user is not keepLogged then return cache result`() = runTest {
        coEvery { cache.getKeepLogged() } returns false

        val keepLogged = repository.getKeepLogged()

        Assert.assertEquals(false, keepLogged)
    }
}
