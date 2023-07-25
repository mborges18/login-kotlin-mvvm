package com.example.loginmvvm.access.signin.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.loginmvvm.access.signin.data.repository.SignInRepository
import com.example.loginmvvm.access.signin.domain.SignInModel
import com.example.loginmvvm.common.result.ResultState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInViewModelTest {

    @get:Rule
    var testSchedulerRule = InstantTaskExecutorRule()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("Unit thread")

    private lateinit var repository: SignInRepository
    private lateinit var model: SignInModel
    private lateinit var viewModel: SignInViewModel

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

    private fun setViewModel() {
        repository = mockk(relaxed = true)
        model = SignInModel()
        viewModel = SignInViewModel(repository, model)
        setData()
    }

    private fun setData() {
        viewModel.setEmail("teste@teste.com")
        viewModel.setPassword("A@123456")
    }

    @Test
    fun `when call signIn then verify repository is called`()  = runTest {
        setViewModel()
        val result: ResultState<Boolean> = mockk(relaxed = true)
        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        delay(2000)
        coVerify {
            repository.signIn(keepLogged = true, model = model)
        }
    }

    @Test
    fun `when call signIn then verify repository returns success`() = runTest {
        setViewModel()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<Boolean> = ResultState.Success(true)

        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        delay(2000)
        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signIn then verify repository returns not found`() = runTest {
        setViewModel()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<Boolean> = ResultState.NotFound

        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        delay(2000)
        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signIn then verify repository returns error`() = runTest {
        setViewModel()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<Boolean> = ResultState.Error

        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        delay(2000)
        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signIn then verify repository returns failure`() = runTest {
        setViewModel()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<Boolean> = ResultState.Failure

        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        delay(2000)
        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signIn then verify email returns invalid`() = runTest {
        setViewModel()
        val emailObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.emailError.observeForever(emailObserver)

        viewModel.setEmail("email")
        viewModel.signIn(keepLogged = true)
        delay(2000)
        verify {
            emailObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signIn then verify password returns invalid`() = runTest {
        setViewModel()
        val passwordObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.passwordError.observeForever(passwordObserver)

        viewModel.setPassword("126")
        viewModel.signIn(keepLogged = true)
        delay(2000)
        verify {
            passwordObserver.onChanged(true)
        }
    }
}
