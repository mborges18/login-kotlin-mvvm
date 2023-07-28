package com.example.loginmvvm.access.signin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.access.signin.data.repository.SignInRepository
import com.example.loginmvvm.access.signin.model.SignInModel
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.common.validation.isEmailValid
import com.example.loginmvvm.common.validation.isPasswordValid
import kotlinx.coroutines.launch

class SignInViewModel(
    private val repository: SignInRepository,
    private val model: SignInModel
): ViewModel() {

    private val _uiState = MutableLiveData<ResultState<Any>>()
    val uiState = _uiState as LiveData<ResultState<Any>>

    private val _fillFieldsAfterSignUp = MutableLiveData(false)
    val fillFieldsAfterSignUp = _fillFieldsAfterSignUp as LiveData<Boolean>

    private val _enableButton = MutableLiveData(false)
    val enableButton = _enableButton as LiveData<Boolean>

    private val _emailError = MutableLiveData<Boolean>()
    val emailError = _emailError as LiveData<Boolean>

    private val _passwordError = MutableLiveData<Boolean>()
    val passwordError = _passwordError as LiveData<Boolean>

    fun setEmail(email: String) {
        model.apply {
            this.email = email
            handlerEnabledButton(this)
        }
    }

    fun setPassword(password: String) {
        model.apply {
            this.password = password
            handlerEnabledButton(this)
        }
    }

    fun signIn(keepLogged: Boolean) = viewModelScope.launch {
        if (isInvalidSignIn().not()) {
            _uiState.postValue(ResultState.Loading)
            val response = repository.signIn(model, keepLogged)
            _uiState.postValue(response)
        }
    }

    private fun isInvalidSignIn(): Boolean {
        var isError = false
        if (model.email.isEmailValid().not()) {
            isError = true
            _emailError.postValue(true)
        }
        if (model.password.isPasswordValid().not()) {
            isError = true
            _passwordError.postValue(true)
        }
        return isError
    }

    private fun handlerEnabledButton(loginModel: SignInModel) {
        _enableButton.postValue(loginModel.email.isNotEmpty() && loginModel.password.isNotEmpty())
    }

    fun fillFieldsAfterSignUp() {
        if (model.email.isEmpty() && model.password.isEmpty()) {
            _fillFieldsAfterSignUp.postValue(true)
        }
    }
}
