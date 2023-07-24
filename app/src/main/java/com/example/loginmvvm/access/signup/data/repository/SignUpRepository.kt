package com.example.loginmvvm.access.signup.data.repository

import com.example.loginmvvm.access.signup.data.api.SignUpApi
import com.example.loginmvvm.access.signup.domain.SignUpModel
import com.example.loginmvvm.access.signup.domain.toRequest
import com.example.loginmvvm.common.data.local.cache.Cache
import com.example.loginmvvm.common.result.ResultState

class SignUpRepositoryImpl(
    private val api: SignUpApi
): SignUpRepository {

    override suspend fun signUp(model: SignUpModel): ResultState<SignUpModel> {
        return try {
            val response = api.signUp(model.toRequest())
            if(response.isSuccessful) {
                ResultState.Success(model)
            } else if(response.code() == 409){
                ResultState.Exists
            }else{
                ResultState.Error
            }
        } catch (e: Throwable) {
            ResultState.Failure
        }
    }
}

interface SignUpRepository {
    suspend fun signUp(model: SignUpModel): ResultState<SignUpModel>
}