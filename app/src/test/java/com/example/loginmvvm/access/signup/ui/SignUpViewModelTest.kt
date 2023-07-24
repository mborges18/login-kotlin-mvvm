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

class SignUpViewModelTest {

    @get:Rule
    var testTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("Unit thread")

    private val repository: SignUpRepository = mockk(relaxed = true)
    private val model: SignUpModel = SignUpModel()
    private var viewModel: SignUpViewModel = SignUpViewModel(repository, model)

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
    fun `when call signUp then verify repository is called`() {
        val result: ResultState<SignUpModel> = mockk(relaxed = true)
        coEvery { repository.signUp(model = model) } returns result

        setData()
        viewModel.signUp()

        coVerify {
            repository.signUp(model = model)
        }
    }

    @Test
    fun `when call signUp then verify repository returns success`() {
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<SignUpModel> = ResultState.Success(mockk(relaxed = true))

        coEvery { repository.signUp(model = model) } returns result
        setData()
        viewModel.signUp()

        verify {
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signUp then verify repository returns error`() {
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<SignUpModel> = ResultState.Error

        coEvery { repository.signUp(model = model) } returns result
        setData()
        viewModel.signUp()

        verify {
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signUp then verify repository returns exists`() {
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<SignUpModel> = ResultState.Exists

        coEvery { repository.signUp(model = model) } returns result
        setData()
        viewModel.signUp()

        verify {
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signUp then verify repository returns failure`() {
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<SignUpModel> = ResultState.Failure

        coEvery { repository.signUp(model = model) } returns result
        setData()
        viewModel.signUp()

        verify {
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call signUp then verify valid name`() {
        val errorNameObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.nameError.observeForever(errorNameObserver)

        viewModel.setName("Marcio")
        viewModel.signUp()

        verify {
            errorNameObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid birthdate`() {
        val errorBirthdateObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.birthDateError.observeForever(errorBirthdateObserver)

        viewModel.setBirthDate("18/11/198")
        viewModel.signUp()

        verify {
            errorBirthdateObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid phone`() {
        val errorPhoneObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.phoneError.observeForever(errorPhoneObserver)

        viewModel.setPhone("(81) 98620-185")
        viewModel.signUp()

        verify {
            errorPhoneObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid email`() {
        val errorEmailObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.emailError.observeForever(errorEmailObserver)

        viewModel.setEmail("email")
        viewModel.signUp()

        verify {
            errorEmailObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid password`()  {
        val errorPassObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.passwordError.observeForever(errorPassObserver)

        viewModel.setPassword("123")
        viewModel.signUp()

        verify {
            errorPassObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid confirm password`() {
        val errorConfirmPassObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.confirmPasswordError.observeForever(errorConfirmPassObserver)

        viewModel.setConfirmPassword("123")
        viewModel.signUp()

        verify {
            errorConfirmPassObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid infos password`() {
        val errorInfosPassObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.passwordsInfo.observeForever(errorInfosPassObserver)

        viewModel.setPassword("123")
        viewModel.signUp()

        verify {
            errorInfosPassObserver.onChanged(true)
        }
    }

    @Test
    fun `when call signUp then verify valid difference password`() {
        val errorDiffPassObserver: Observer<Boolean> = mockk(relaxed = true)
        viewModel.differentPasswords.observeForever(errorDiffPassObserver)

        viewModel.setPassword("123456")
        viewModel.setConfirmPassword("123457")
        viewModel.signUp()

        verify {
            errorDiffPassObserver.onChanged(true)
        }
    }
}
