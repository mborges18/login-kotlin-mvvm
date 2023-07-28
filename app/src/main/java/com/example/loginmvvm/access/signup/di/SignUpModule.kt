package com.example.loginmvvm.access.signup.di

import com.example.loginmvvm.access.signup.data.api.SignUpApi
import com.example.loginmvvm.access.signup.data.repository.SignUpRepository
import com.example.loginmvvm.access.signup.data.repository.SignUpRepositoryImpl
import com.example.loginmvvm.access.signup.model.SignUpModel
import com.example.loginmvvm.access.signup.ui.SignUpViewModel
import com.example.loginmvvm.common.data.remote.ApiClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SignUpModule {
    val instance = module {
        single { ApiClient.init(SignUpApi::class.java) }
        factory<SignUpRepository> { SignUpRepositoryImpl(api = get()) }
        factory { SignUpModel() }
        viewModel { SignUpViewModel(repository = get(), model = get()) }
    }
}
