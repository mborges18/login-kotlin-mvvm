package com.example.loginmvvm.access.signin.model

import com.example.loginmvvm.access.signin.data.model.SignInRequest

data class SignInModel(
    var email: String = "",
    var password: String = ""
)

fun SignInModel.toRequest(): SignInRequest {
    return SignInRequest(
        email = email,
        password = password
    )
}
