package com.example.loginmvvm.splash.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.splash.data.repository.SplashRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: SplashRepository
): ViewModel() {

    private val _keepLogged: MutableLiveData<Boolean> = MutableLiveData()
    val keepLogged = _keepLogged as LiveData<Boolean>

    fun getKeepLogged() = viewModelScope.launch {
        val response = repository.getKeepLogged()
        _keepLogged.postValue(response)
    }
}
