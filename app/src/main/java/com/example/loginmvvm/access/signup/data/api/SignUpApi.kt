package com.example.loginmvvm.access.signup.data.api

import com.example.loginmvvm.access.signup.data.model.SignUpRequest
import com.example.loginmvvm.access.signup.data.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {
    @POST("signup")
    suspend fun signUp(@Body signUp: SignUpRequest): Response<Unit>
}