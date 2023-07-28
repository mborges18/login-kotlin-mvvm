package com.example.loginmvvm.access.signup.model

import android.os.Parcelable
import com.example.loginmvvm.access.signup.data.model.SignUpRequest
import com.example.loginmvvm.common.date.DateUtil.toDateAmerica
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpModel(
    var name: String = "",
    var birthDate: String = "",
    var email: String = "",
    var phone: String = "",
    var typeMember: TypeMemberEnum = TypeMemberEnum.BRONZE,
    var status: TypeStatusEnum = TypeStatusEnum.ACTIVE,
    var password: String = "",
    var confirmPassword: String = "",
): Parcelable

fun SignUpModel.toRequest(): SignUpRequest {
    return SignUpRequest(
        name = name,
        birthDate = birthDate.toDateAmerica(),
        email = email,
        phone = phone,
        typeMember = typeMember.typeMember,
        status = status.typeStatus,
        password = password
    )
}
