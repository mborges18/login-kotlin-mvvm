package com.example.loginmvvm.home.data.model

import com.example.loginmvvm.common.date.DateUtil.toDateBrazilian
import com.example.loginmvvm.home.model.CustomerModel
import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    @SerializedName("name") val name: String,
    @SerializedName("birthdate") val birthdate: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("type") val type: String
)

fun CustomerResponse.toModel() : CustomerModel =
    CustomerModel(
        name = name,
        birthdate = birthdate.toDateBrazilian(),
        email = email,
        phone = phone,
        type = type
    )
