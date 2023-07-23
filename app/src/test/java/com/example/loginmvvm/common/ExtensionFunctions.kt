package com.example.loginmvvm.common

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> MockWebServer.create(service: Class<T>): T {
    this.apply {
        enqueue(MockResponse().setResponseCode(500))
        start()
    }
    return Retrofit.Builder()
        .baseUrl(this.url("/"))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(service)
}

fun Any?.isNotNull() = this != null