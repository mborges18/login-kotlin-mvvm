package com.example.loginmvvm.common.data.remote

import com.example.loginmvvm.BuildConfig.DEBUG
import com.example.loginmvvm.BuildConfig.BASE_API_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val TIME = 30L

    fun <T> init(api: Class<T>): T {
        val logger = HttpLoggingInterceptor()
        logger.level = if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .connectTimeout(TIME, TimeUnit.SECONDS)
            .readTimeout(TIME, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .addInterceptor(ServiceInterceptor())
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}

class ServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val modified = response.newBuilder()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .build()

        return modified
    }
}