package com.example.loginmvvm.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.home.data.repository.HomeRepository
import com.example.loginmvvm.home.model.CustomerModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
): ViewModel() {

    private val _uiState: MutableLiveData<ResultState<CustomerModel>> = MutableLiveData()
    val uiState = _uiState as LiveData<ResultState<CustomerModel>>

    private val _logout: MutableLiveData<Boolean> = MutableLiveData(false)
    val logout = _logout as LiveData<Boolean>

    fun getCustomer() = viewModelScope.launch {
        _uiState.postValue(ResultState.Loading)
        val response = repository.getCustomer()
        _uiState.postValue(response)
    }

    fun logout() = viewModelScope.launch {
        val response = repository.logout()
        _logout.postValue(response)
    }
}
