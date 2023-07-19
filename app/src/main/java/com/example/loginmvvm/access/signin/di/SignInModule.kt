package com.example.loginmvvm.access.signin.di

import com.example.loginmvvm.access.signin.data.api.SignInApi
import com.example.loginmvvm.access.signin.data.repository.SignInRepository
import com.example.loginmvvm.access.signin.data.repository.SignInRepositoryImpl
import com.example.loginmvvm.access.signin.model.SignInModel
import com.example.loginmvvm.access.signin.ui.SignInViewModel
import com.example.loginmvvm.common.data.remote.ApiClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SignInModule {
    val instance = module {
        single { ApiClient.init(SignInApi::class.java) }
        factory<SignInRepository> { SignInRepositoryImpl(api = get(), cache = get()) }
        factory { SignInModel() }
        viewModel { SignInViewModel(repository = get(), model = get()) }
    }
}