package com.example.loginmvvm.home.data.api

import com.example.loginmvvm.home.data.model.CustomerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface HomeApi {
    @GET("customer")
    suspend fun getCustomer(@Header("iduser") idUser: String): Response<CustomerResponse>
}
