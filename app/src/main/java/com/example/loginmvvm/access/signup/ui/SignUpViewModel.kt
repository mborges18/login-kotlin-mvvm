package com.example.loginmvvm.access.signup.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.access.signup.data.repository.SignUpRepository
import com.example.loginmvvm.access.signup.model.SignUpModel
import com.example.loginmvvm.access.signup.model.TypeMemberEnum
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.common.validation.isBirthDateValid
import com.example.loginmvvm.common.validation.isCellPhoneValid
import com.example.loginmvvm.common.validation.isEmailValid
import com.example.loginmvvm.common.validation.isNameValid
import com.example.loginmvvm.common.validation.isPasswordValid
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: SignUpRepository,
    private val model: SignUpModel
): ViewModel() {

    private val _uiState = MutableLiveData<ResultState<SignUpModel>>()
    val uiState = _uiState as LiveData<ResultState<SignUpModel>>

    private val _enableButton = MutableLiveData(false)
    val enableButton = _enableButton as LiveData<Boolean>

    private val _nameError = MutableLiveData(false)
    val nameError = _nameError as LiveData<Boolean>

    private val _birthDateError = MutableLiveData(false)
    val birthDateError = _birthDateError as LiveData<Boolean>

    private val _phoneError = MutableLiveData(false)
    val phoneError = _phoneError as LiveData<Boolean>

    private val _emailError = MutableLiveData(false)
    val emailError = _emailError as LiveData<Boolean>

    private val _passwordError = MutableLiveData(false)
    val passwordError = _passwordError as LiveData<Boolean>

    private val _confirmPasswordError = MutableLiveData(false)
    val confirmPasswordError = _confirmPasswordError as LiveData<Boolean>

    private val _differentPasswords = MutableLiveData(false)
    val differentPasswords = _differentPasswords as LiveData<Boolean>

    fun setName(name: String) {
        model.apply {
            this.name = name
            handlerEnabledButton(this)
        }
    }

    fun setBirthDate(birthDate: String) {
        model.apply {
            this.birthDate = birthDate
            handlerEnabledButton(this)
        }
    }

    fun setPhone(phone: String) {
        model.apply {
            this.phone = phone
            handlerEnabledButton(this)
        }
    }

    fun setEmail(email: String) {
        model.apply {
            this.email = email
            handlerEnabledButton(this)
        }
    }

    fun setTypeMember(typeMember: String) {
        this.model.apply {
            this.typeMember = TypeMemberEnum.from(typeMember)
        }
    }

    fun setPassword(password: String) {
        model.apply {
            this.password = password
            handlerEnabledButton(this)
        }
    }

    fun setConfirmPassword(confirmPassword: String) {
        model.apply {
            this.confirmPassword = confirmPassword
            handlerEnabledButton(this)
        }
    }

    private fun handlerEnabledButton(user: SignUpModel) {
        _enableButton.value =
            (user.name.isNotEmpty() &&
                    user.birthDate.isNotEmpty() &&
                    user.phone.isNotEmpty() &&
                    user.email.isNotEmpty() &&
                    user.password.isNotEmpty() &&
                    user.confirmPassword.isNotEmpty())
    }


    fun signUp() = viewModelScope.launch {
        if (isInvalidSignUp().not()) {
            _uiState.postValue(ResultState.Loading)
            val response = repository.signUp(model)
            _uiState.postValue(response)
        }
    }

    private fun isInvalidSignUp(): Boolean {
        var isError = false

        if (model.name.isNameValid().not()) {
            isError = true
            _nameError.value = true
        }
        if (model.birthDate.isBirthDateValid().not()) {
            isError = true
            _birthDateError.value = true
        }
        if (model.phone.isCellPhoneValid().not()) {
            isError = true
            _phoneError.value = true
        }
        if (model.email.isEmailValid().not()) {
            isError = true
            _emailError.value = true
        }
        if (model.password.isPasswordValid().not()) {
            isError = true
            _passwordError.value = true
        }
        if (model.confirmPassword.isPasswordValid().not()) {
            isError = true
            _confirmPasswordError.value = true
        }

        if (
            isDifferentPassword(model) &&
            model.confirmPassword != model.password
        ) {
            isError = true
            _differentPasswords.value = true
        }

        return isError
    }

    private fun isDifferentPassword(user: SignUpModel) =
        user.password.isNotEmpty() && user.confirmPassword.isNotEmpty() &&
            user.password.isPasswordValid() && user.confirmPassword.isPasswordValid()
}
