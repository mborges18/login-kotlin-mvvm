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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading as LiveData<Boolean>

    private val _enableFields = MutableLiveData<Boolean>()
    val enableFields = _enableFields as LiveData<Boolean>

    private val _enableButton = MutableLiveData<Boolean>()
    val enableButton = _enableButton as LiveData<Boolean>

    private val _signInSuccessResponse = MutableLiveData<Boolean>()
    val signInSuccessResponse = _signInSuccessResponse as LiveData<Boolean>

    private val _signInErrorResponse = MutableLiveData<Boolean>()
    val signInErrorResponse = _signInErrorResponse as LiveData<Boolean>

    private val _signInNotFoundResponse = MutableLiveData<Boolean>()
    val signInNotFoundResponse = _signInNotFoundResponse as LiveData<Boolean>

    private val _signInFailureResponse = MutableLiveData<Boolean>()
    val signInFailureResponse = _signInFailureResponse as LiveData<Boolean>

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