package com.example.loginmvvm.access.signin.data.api

import com.example.loginmvvm.access.signin.data.model.SignInRequest
import com.example.loginmvvm.access.signin.data.model.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi {
    @POST("signin")
    suspend fun signIn(@Body model: SignInRequest): Response<SignInResponse>
}
