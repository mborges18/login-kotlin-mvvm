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

    private val _isLoading = MutableLiveData(false)
    val isLoading = _isLoading as LiveData<Boolean>

    private val _enableFields = MutableLiveData(true)
    val enableFields = _enableFields as LiveData<Boolean>

    private val _enableButton = MutableLiveData(false)
    val enableButton = _enableButton as LiveData<Boolean>

    private val _signInSuccessResponse = MutableLiveData(false)
    val signInSuccessResponse = _signInSuccessResponse as LiveData<Boolean>

    private val _signInErrorResponse = MutableLiveData(false)
    val signInErrorResponse = _signInErrorResponse as LiveData<Boolean>

    private val _signInNotFoundResponse = MutableLiveData(false)
    val signInNotFoundResponse = _signInNotFoundResponse as LiveData<Boolean>

    private val _signInFailureResponse = MutableLiveData(false)
    val signInFailureResponse = _signInFailureResponse as LiveData<Boolean>

    private val _emailError = MutableLiveData(false)
    val emailError = _emailError as LiveData<Boolean>

    private val _passwordError = MutableLiveData(false)
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
            _isLoading.value = true
            _enableFields.value = false
            when(repository.signIn(model, keepLogged)){
                is ResultState.Success -> {
                    _signInSuccessResponse.value = true
                }
                is ResultState.NotFound -> {
                    _signInNotFoundResponse.value = true
                }
                is ResultState.Error -> {
                    _signInErrorResponse.value = true
                }
                else -> {
                    _signInFailureResponse.value = true
                }
            }
            _isLoading.value = false
            _enableFields.value = true
        }
    }

    private fun isInvalidSignIn(): Boolean {
        var isError = false
        if (model.email.isEmailValid().not()) {
            isError = true
            _emailError.value = true
        }
        if (model.password.isPasswordValid().not()) {
            isError = true
            _passwordError.value = true
        }
        return isError
    }

    private fun handlerEnabledButton(loginModel: SignInModel) {
        _enableButton.value = loginModel.email.isNotEmpty() && loginModel.password.isNotEmpty()
    }
}