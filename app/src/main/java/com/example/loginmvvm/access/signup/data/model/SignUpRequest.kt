package com.example.loginmvvm.access.signup.data.model

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("name") var name: String = "",
    @SerializedName("birthdate") var birthDate: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("phone") var phone: String = "",
    @SerializedName("type") var typeMember: String = "",
    @SerializedName("status") var status: String = "",
    @SerializedName("password") var password: String = "",
)