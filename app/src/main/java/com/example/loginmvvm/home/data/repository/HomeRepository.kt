package com.example.loginmvvm.home.data.repository

import com.example.loginmvvm.common.data.SUCCESS
import com.example.loginmvvm.common.data.local.cache.Cache
import com.example.loginmvvm.common.data.local.cache.CacheImpl.Companion.USER_ID
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.home.data.api.HomeApi
import com.example.loginmvvm.home.data.model.toModel
import com.example.loginmvvm.home.domain.CustomerModel

class HomeRepositoryImpl(
    private val api: HomeApi,
    private val cache: Cache
) : HomeRepository {

    override suspend fun getCustomer(): ResultState<CustomerModel> {
        return try {
            val idUser = cache.get(USER_ID)
            val response = api.getCustomer(idUser)
            when (response.code()) {
                SUCCESS -> ResultState.Success(response.body()!!.toModel())
                else -> ResultState.Error
            }
        } catch (e: Throwable) {
            ResultState.Failure
        }
    }
}

interface HomeRepository {
    suspend fun getCustomer(): ResultState<CustomerModel>
}
