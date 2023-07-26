package com.example.loginmvvm.access.signin.data.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("iduser") val idUser: String,
)
