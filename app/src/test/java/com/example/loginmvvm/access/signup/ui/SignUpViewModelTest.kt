package com.example.loginmvvm.access.signup.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.loginmvvm.access.signup.data.repository.SignUpRepository
import com.example.loginmvvm.access.signup.domain.SignUpModel
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpViewModelTest {

    @get:Rule
    var testSchedulerRule = InstantTaskExecutorRule()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("Unit thread")

    private lateinit var repository: SignUpRepository
    private lateinit var model: SignUpModel
    private lateinit var viewModel: SignUpViewModel

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
        model = SignUpModel()
        viewModel = SignUpViewModel(repository, model)
        setData()
    }

    private fun setData() {
        viewModel.setName("Marcio Borges")
        viewModel.setBirthDate("18/11/1981")
        viewModel.setPhone("(81) 98620-1853")
        viewModel.setTypeMember("LEADER")
        viewModel.setEmail("teste@teste.com")
        viewModel.setPassword("A@123456")
        viewModel.setConfirmPassword("A@123456")
    }

    @Test
    fun `when call signUp then verify repository is called`() = runTest {
        setViewModel()
        val result: ResultState<SignUpModel> = mockk(relaxed = true)
        coEvery { repository.signUp(model = model) } returns result

        viewModel.signUp()

        coVerify {
            launch { delay(1000) }
            repository.signUp(model = model)
        }
    }

    @Test
    fun `when call signUp then verify repository returns success`() = runTest {
        setViewModel()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<SignUpModel> = ResultState.Success(mockk(relaxed = true))

        coEvery { repository.signUp(model = model) } returns result
        viewModel.signUp()

        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            launch { delay(1000) }
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signUp then verify repository returns error`()  = runTest {
        setViewModel()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<SignUpModel> = ResultState.Error

        coEvery { repository.signUp(model = model) } returns result
        viewModel.signUp()

        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            launch { delay(1000) }
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signUp then verify repository returns exists`() = runTest {
        setViewModel()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<SignUpModel> = ResultState.Exists

        coEvery { repository.signUp(model = model) } returns result
        viewModel.signUp()

        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            launch { delay(1000) }
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signUp then verify repository returns failure`() = runTest {
        setViewModel()
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<SignUpModel> = ResultState.Failure

        coEvery { repository.signUp(model = model) } returns result
        viewModel.signUp()

        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            launch { delay(1000) }
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signUp then verify valid name`()  = runTest {
        setViewModel()
        val errorNameObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.nameError.observeForever(errorNameObserver)

        viewModel.setName("Marcio")
        viewModel.signUp()

        verify {
            launch { delay(1000) }
            errorNameObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid birthdate`() = runTest {
        setViewModel()
        val errorBirthdateObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.birthDateError.observeForever(errorBirthdateObserver)

        viewModel.setBirthDate("18/11/198")
        viewModel.signUp()

        verify {
            launch { delay(1000) }
            errorBirthdateObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid phone`() = runTest {
        setViewModel()
        val errorPhoneObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.phoneError.observeForever(errorPhoneObserver)

        viewModel.setPhone("(81) 98620-185")
        viewModel.signUp()

        verify {
            launch { delay(1000) }
            errorPhoneObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid email`() = runTest {
        setViewModel()
        val errorEmailObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.emailError.observeForever(errorEmailObserver)

        viewModel.setEmail("email")
        viewModel.signUp()

        verify {
            launch { delay(1000) }
            errorEmailObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid password`() = runTest {
        setViewModel()
        val errorPassObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.passwordError.observeForever(errorPassObserver)

        viewModel.setPassword("123")
        viewModel.signUp()

        verify {
            launch { delay(1000) }
            errorPassObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid confirm password`() = runTest {
        setViewModel()
        val errorConfirmPassObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.confirmPasswordError.observeForever(errorConfirmPassObserver)

        viewModel.setConfirmPassword("123")
        viewModel.signUp()

        verify {
            launch { delay(1000) }
            errorConfirmPassObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid difference password`() = runTest {
        setViewModel()
        val errorDiffPassObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.differentPasswords.observeForever(errorDiffPassObserver)

        viewModel.setPassword("123456")
        viewModel.setConfirmPassword("123457")
        viewModel.signUp()

        verify {
            launch { delay(1000) }
            errorDiffPassObserver.onChanged(true)
        }
    }
}
