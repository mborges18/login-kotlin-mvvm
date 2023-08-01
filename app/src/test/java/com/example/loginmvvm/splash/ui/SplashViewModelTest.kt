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
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
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
    private val observerConnected : Observer<Unit> = mockk(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when call repository then verify repository is called`() = runTest {
        coEvery { repository.getKeepLogged() } returns true

        viewModel.getKeepLogged()
        delay(2000)
        coVerify { repository.getKeepLogged() }
    }

    @Test
    fun `when call getKeepLogged then return user connected`() = runTest {
        viewModel.gotoHome.observeForever(observerConnected)

        coEvery { repository.getKeepLogged() } returns true

        viewModel.getKeepLogged()
        delay(2000)
        verify { observerConnected.onChanged(Unit) }
    }

    @Test
    fun `when call getKeepLogged then return user not connected`() = runTest {
        viewModel.gotoAccess.observeForever(observerConnected)

        coEvery { repository.getKeepLogged() } returns false

        viewModel.getKeepLogged()
        delay(2000)
        verify { observerConnected.onChanged(Unit) }
    }
}
