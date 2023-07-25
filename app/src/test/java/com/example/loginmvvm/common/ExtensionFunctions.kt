package com.example.loginmvvm.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.jetbrains.annotations.VisibleForTesting
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@VisibleForTesting
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

@VisibleForTesting
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}