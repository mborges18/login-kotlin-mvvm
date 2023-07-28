package com.example.loginmvvm.home.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.home.data.repository.HomeRepository
import com.example.loginmvvm.home.model.CustomerModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
class HomeViewModelTest {

    @get:Rule
    var testSchedulerRule = InstantTaskExecutorRule()

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val repository: HomeRepository = mockk()
    private val viewModel: HomeViewModel = HomeViewModel(repository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when call get customer then verify repository is called`(){
        coEvery { repository.getCustomer() } returns ResultState.Success(mockk())

        viewModel.getCustomer()

        coVerify {
            repository.getCustomer()
        }
    }

    @Test
    fun `when call get customer then verify repository returns success`(){
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<CustomerModel> = ResultState.Success(mockk())

        coEvery { repository.getCustomer() } returns result

        viewModel.getCustomer()

        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call get customer then verify repository returns error`(){
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<CustomerModel> = ResultState.Error

        coEvery { repository.getCustomer() } returns result

        viewModel.getCustomer()

        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }

    @Test
    fun `when call get customer then verify repository returns failure`(){
        val uiStateObserver: Observer<ResultState<Any>> = mockk(relaxed = true)
        viewModel.uiState.observeForever(uiStateObserver)
        val result: ResultState<CustomerModel> = ResultState.Failure

        coEvery { repository.getCustomer() } returns result

        viewModel.getCustomer()

        verifySequence {
            uiStateObserver.onChanged(ResultState.Loading)
            uiStateObserver.onChanged(result)
        }
    }
}
