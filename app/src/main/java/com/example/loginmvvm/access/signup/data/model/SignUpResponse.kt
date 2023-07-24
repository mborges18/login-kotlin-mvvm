package com.example.loginmvvm.access.signup.data.model

import com.example.loginmvvm.access.signup.domain.TypeMemberEnum
import com.example.loginmvvm.access.signup.domain.TypeStatusEnum
import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("ROWID") var id: String = "",
    @SerializedName("CREATORID") var creatorId: String = "",
    @SerializedName("CREATEDTIME") var createdTime: String = "",
    @SerializedName("MODIFIEDTIME") var modifiedTime: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("birthdate") var birthDate: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("phone") var phone: String = "",
    @SerializedName("type") var typeMember: TypeMemberEnum,
    @SerializedName("status") var status: TypeStatusEnum,
)