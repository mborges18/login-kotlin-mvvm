package com.example.loginmvvm.access.signin.data.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("ROWID") val idUser: String,
)
