package com.example.loginmvvm.splash.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.loginmvvm.splash.data.repository.SplashRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashViewModelTest {

    @get:Rule
    var testSchedulerRule = InstantTaskExecutorRule()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("Unit thread")

    private val repository: SplashRepository = mockk(relaxed = true)
    private val viewModel: SplashViewModel = SplashViewModel(repository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `when call repository then verify repository is called`() {
        coEvery { repository.getKeepLogged() } returns true

        viewModel.getKeepLogged()

        coVerify { repository.getKeepLogged() }
    }

    @Test
    fun `when call getKeepLogged then return user connected`() {
        val observerConnected : Observer<Boolean> = mockk(relaxed = true)
        viewModel.keepLogged.observeForever(observerConnected)

        coEvery { repository.getKeepLogged() } returns true

        viewModel.getKeepLogged()

        coVerify { observerConnected.onChanged(true) }
    }

    @Test
    fun `when call getKeepLogged then return user not connected`() {
        val observerConnected : Observer<Boolean> = mockk(relaxed = true)
        viewModel.keepLogged.observeForever(observerConnected)

        coEvery { repository.getKeepLogged() } returns false

        viewModel.getKeepLogged()

        coVerify { observerConnected.onChanged(false) }
    }
}
