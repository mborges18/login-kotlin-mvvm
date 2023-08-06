package com.example.loginmvvm.home

import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.home.data.repository.HomeRepository
import com.example.loginmvvm.home.model.CustomerModel
import com.example.loginmvvm.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object HomeModule {

    private lateinit var homeRepository: HomeRepository

    fun getModuleHomeDataCustomer(): Module {
        homeRepository = object : HomeRepository {
            override suspend fun getCustomer(): ResultState<CustomerModel> {
                return ResultState.Success(getDataCustomer())
            }

            override suspend fun logout() = false
        }

        return module {
            viewModel { HomeViewModel(homeRepository) }
        }
    }

    private fun getDataCustomer() = CustomerModel(
        name = "Marcos Paulo",
        email = "marcospaulo@gmail.com",
        birthdate = "18/11/1981",
        phone = "(81) 98634-1234",
        type = "GOLD"
    )
}
