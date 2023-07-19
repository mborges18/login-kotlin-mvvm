package com.example.loginmvvm.common.result

sealed class ResultState<out T> {
    data class Success<out T>(val data: T): ResultState<T>()
    data class Failure<out T>(val throwable: Throwable) : ResultState<T>()
    data class Error<out T>(var message: String) : ResultState<T>()
    class Exists<out T> : ResultState<T>()
    class NotFound<out T> : ResultState<T>()
}