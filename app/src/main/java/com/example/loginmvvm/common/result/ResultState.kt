package com.example.loginmvvm.common.result

sealed class ResultState<out T> {
    data class Success<out T>(val data: T): ResultState<T>()
    data class Error<out T>(var message: String) : ResultState<T>()
    data class Failure<out T>(val throwable: Throwable) : ResultState<T>()
    object Exists: ResultState<Nothing>()
    object NotFound: ResultState<Nothing>()
    object Loading: ResultState<Nothing>()
}
