package com.example.loginmvvm.home.data.api

import com.example.loginmvvm.home.data.model.CustomerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("customer/{idUser}")
    suspend fun getCustomer(@Path("idUser") idUser: String): Response<CustomerResponse>
}
