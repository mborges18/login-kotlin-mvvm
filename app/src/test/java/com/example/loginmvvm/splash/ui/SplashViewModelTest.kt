package com.example.loginmvvm.splash.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.loginmvvm.splash.data.repository.SplashRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModelTest {

    @get:Rule
    var testSchedulerRule = InstantTaskExecutorRule()

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val repository: SplashRepository = mockk()
    private val viewModel: SplashViewModel = SplashViewModel(repository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
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

        verify { observerConnected.onChanged(true) }
    }

    @Test
    fun `when call getKeepLogged then return user not connected`() {
        val observerConnected : Observer<Boolean> = mockk(relaxed = true)
        viewModel.keepLogged.observeForever(observerConnected)

        coEvery { repository.getKeepLogged() } returns false

        viewModel.getKeepLogged()

        verify { observerConnected.onChanged(false) }
    }
}
