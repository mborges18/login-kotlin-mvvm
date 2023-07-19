package com.example.loginmvvm.access.signin.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.loginmvvm.access.signin.data.repository.SignInRepository
import com.example.loginmvvm.access.signin.model.SignInModel
import com.example.loginmvvm.common.result.ResultState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
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
    }

    private fun setData() {
        viewModel.setEmail("teste@teste.com")
        viewModel.setPassword("A@123456")
    }

    @Test
    fun `when call signIn then verify repository is called`() {
        setViewModel()
        val result: ResultState<Boolean> = mockk(relaxed = true)
        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        setData()
        viewModel.signIn(keepLogged = true)

        coVerify {
            repository.signIn(keepLogged = true, model = model)
        }
    }

    @Test
    fun `when call signIn then verify repository returns success`() {
        setViewModel()
        val signInObserver: Observer<Boolean> = mockk(relaxed = true)
        val responseSuccess: ResultState<Boolean> = ResultState.Success(true)
        viewModel.signInSuccessResponse.observeForever(signInObserver)

        coEvery { repository.signIn(keepLogged = true, model = model) } returns responseSuccess

        setData()
        viewModel.signIn(keepLogged = true)

        verify {
            if(responseSuccess is ResultState.Success){
                signInObserver.onChanged(responseSuccess.data)
            }
        }
    }

    @Test
    fun `when call signIn then verify repository returns not found`() {
        setViewModel()
        val signInObserver: Observer<Boolean> = mockk(relaxed = true)
        val responseNotFound: ResultState<Boolean> = ResultState.NotFound()
        viewModel.signInNotFoundResponse.observeForever(signInObserver)

        coEvery { repository.signIn(keepLogged = true, model = model) } returns responseNotFound

        setData()
        viewModel.signIn(keepLogged = true)

        verify {
            signInObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signIn then verify repository returns error`() {
        setViewModel()
        val signInObserver: Observer<Boolean> = mockk(relaxed = true)
        val responseError: ResultState<Boolean> = ResultState.Error("message")
        viewModel.signInErrorResponse.observeForever(signInObserver)

        coEvery { repository.signIn(keepLogged = true, model = model) } returns responseError

        setData()
        viewModel.signIn(keepLogged = true)

        verify {
            signInObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signIn then verify repository returns failure`() {
        setViewModel()
        val signInObserver: Observer<Boolean> = mockk(relaxed = true)
        val responseFailure: ResultState<Boolean> = ResultState.Failure(Throwable())
        viewModel.signInFailureResponse.observeForever(signInObserver)

        coEvery { repository.signIn(keepLogged = true, model = model) } returns responseFailure

        setData()
        viewModel.signIn(keepLogged = true)

        verify {
            signInObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signIn then verify email returns invalid`() {
        setViewModel()
        val emailObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.emailError.observeForever(emailObserver)

        setData()
        viewModel.setEmail("email")
        viewModel.signIn(keepLogged = true)

        verify {
            emailObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signIn then verify password returns invalid`() {
        setViewModel()
        val passwordObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.passwordError.observeForever(passwordObserver)

        setData()
        viewModel.setPassword("126")
        viewModel.signIn(keepLogged = true)

        verify {
            passwordObserver.onChanged(true)
        }
    }
}