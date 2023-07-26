package com.example.loginmvvm.home.di

import com.example.loginmvvm.common.data.remote.ApiClient
import com.example.loginmvvm.home.data.api.HomeApi
import com.example.loginmvvm.home.data.repository.HomeRepository
import com.example.loginmvvm.home.data.repository.HomeRepositoryImpl
import com.example.loginmvvm.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeModule {
    val instance = module {
        single { ApiClient.init(HomeApi::class.java) }
        factory<HomeRepository> { HomeRepositoryImpl(api = get(), cache = get()) }
        viewModel { HomeViewModel(repository = get()) }
    }
}
