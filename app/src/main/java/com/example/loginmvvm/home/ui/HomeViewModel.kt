package com.example.loginmvvm.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.home.data.repository.HomeRepository
import com.example.loginmvvm.home.domain.CustomerModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
): ViewModel() {

    private val _uiState: MutableLiveData<ResultState<CustomerModel>> = MutableLiveData()
    val uiState = _uiState as LiveData<ResultState<CustomerModel>>

    fun getCustomer() = viewModelScope.launch {
        _uiState.postValue(ResultState.Loading)
        val response = repository.getCustomer()
        _uiState.postValue(response)
    }
}