package com.example.loginmvvm.access.signin.data.repository

import com.example.loginmvvm.access.signin.data.api.SignInApi
import com.example.loginmvvm.access.signin.model.SignInModel
import com.example.loginmvvm.access.signin.model.toRequest
import com.example.loginmvvm.common.data.SUCCESS
import com.example.loginmvvm.common.data.NOT_FOUND
import com.example.loginmvvm.common.data.local.cache.Cache
import com.example.loginmvvm.common.data.local.cache.CacheImpl.Companion.USER_ID
import com.example.loginmvvm.common.data.local.cache.CacheImpl.Companion.USER_KEEP_LOGGED
import com.example.loginmvvm.common.result.ResultState

class SignInRepositoryImpl(
    private val api: SignInApi,
    private val cache: Cache
): SignInRepository {

    override suspend fun signIn(model: SignInModel, keepLogged: Boolean): ResultState<Boolean> {
        return try {
            val response = api.signIn(model.toRequest())
            when(response.code()) {
                SUCCESS -> {
                    cache.insert(USER_KEEP_LOGGED, keepLogged.toString())
                    response.body()?.idUser?.let { cache.insert(USER_ID, it) }
                    ResultState.Success(true)
                }
                NOT_FOUND -> ResultState.NotFound
                else -> ResultState.Error
            }
        } catch (e: Throwable) {
            ResultState.Failure
        }
    }
}

interface SignInRepository {
    suspend fun signIn(model: SignInModel, keepLogged: Boolean): ResultState<Boolean>
}
