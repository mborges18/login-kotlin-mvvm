package com.example.loginmvvm.access.signin.data.api

import com.example.loginmvvm.access.signin.data.model.SignInRequest
import com.example.loginmvvm.access.signin.data.model.SignInResponse
import retrofit2.Response
import retrofit2.http.POST

interface SignInApi {
    @POST("signup")
    suspend fun signIn(model: SignInRequest): Response<SignInResponse>
}