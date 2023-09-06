package com.example.loginmvvm.splash.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.splash.data.repository.SplashRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: SplashRepository,
    private val timeDelay: Long? = null
): ViewModel() {

    private val _gotoHome: MutableLiveData<Unit> = MutableLiveData()
    val gotoHome = _gotoHome as LiveData<Unit>

    private val _gotoAccess: MutableLiveData<Unit> = MutableLiveData()
    val gotoAccess = _gotoAccess as LiveData<Unit>

    fun getKeepLogged() = viewModelScope.launch {
        timeDelay?.let { delay(it) }
        when(repository.getKeepLogged()) {
            true -> _gotoHome.postValue(Unit)
            else -> _gotoAccess.postValue(Unit)
        }
    }
}
