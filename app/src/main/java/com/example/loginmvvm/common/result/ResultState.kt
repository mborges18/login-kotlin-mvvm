package com.example.loginmvvm.common.result

sealed class ResultState<out T> {
    data class Success<out T>(val data: T): ResultState<T>()
    object Error: ResultState<Nothing>()
    object Failure: ResultState<Nothing>()
    object Exists: ResultState<Nothing>()
    object NotFound: ResultState<Nothing>()
    object Loading: ResultState<Nothing>()
}
