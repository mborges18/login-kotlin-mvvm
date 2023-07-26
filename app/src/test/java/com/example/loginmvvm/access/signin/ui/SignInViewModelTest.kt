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
class SignInViewModelTest {

    @get:Rule
    var testSchedulerRule = InstantTaskExecutorRule()

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val repository: SignInRepository = mockk()
    private var model: SignInModel = SignInModel()
    private val viewModel: SignInViewModel = SignInViewModel(repository, model)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun setData() {
        viewModel.setEmail("teste@teste.com")
        viewModel.setPassword("A@123456")
    }

    @Test
    fun `when call signIn then verify repository is called`() {
        setData()
        val result: ResultState<Boolean> = mockk(relaxed = true)
        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        
        coVerify {
            repository.signIn(keepLogged = true, model = model)
        }
    }

    @Test
    fun `when call signIn then verify repository returns success`() {
        setData()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<Boolean> = ResultState.Success(true)

        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        
        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signIn then verify repository returns not found`() {
        setData()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<Boolean> = ResultState.NotFound

        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        
        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signIn then verify repository returns error`() {
        setData()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<Boolean> = ResultState.Error

        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        
        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signIn then verify repository returns failure`() {
        setData()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<Boolean> = ResultState.Failure

        coEvery { repository.signIn(keepLogged = true, model = model) } returns result

        viewModel.signIn(keepLogged = true)
        
        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signIn then verify email returns invalid`() {
        setData()
        val emailObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.emailError.observeForever(emailObserver)

        viewModel.setEmail("email")
        viewModel.signIn(keepLogged = true)
        
        verify {
            emailObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signIn then verify password returns invalid`() {
        setData()
        val passwordObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.passwordError.observeForever(passwordObserver)

        viewModel.setPassword("126")
        viewModel.signIn(keepLogged = true)
        
        verify {
            passwordObserver.onChanged(true)
        }
    }
}
