package com.example.loginmvvm.access.signin

import com.example.loginmvvm.access.signin.data.repository.SignInRepository
import com.example.loginmvvm.access.signin.model.SignInModel
import com.example.loginmvvm.access.signin.ui.SignInViewModel
import com.example.loginmvvm.common.result.ResultState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object SignInModule {

    private lateinit var signInRepository: SignInRepository

    fun getModuleSignInSuccess(): Module {
        signInRepository = object : SignInRepository {
            override suspend fun signIn(
                model: SignInModel,
                keepLogged: Boolean
            ): ResultState<Boolean> {
                return ResultState.Success(true)
            }
        }

        return module {
            viewModel { SignInViewModel(signInRepository, SignInModel()) }
        }
    }

    fun getModuleSignInError(): Module {
        signInRepository = object : SignInRepository {
            override suspend fun signIn(
                model: SignInModel,
                keepLogged: Boolean
            ): ResultState<Boolean> {
                return ResultState.Error
            }
        }

        val signInViewModel = SignInViewModel(signInRepository, SignInModel())

        return module {
            viewModel { signInViewModel }
        }
    }

    fun getModuleSignInNotFound(): Module {
        signInRepository = object : SignInRepository {
            override suspend fun signIn(
                model: SignInModel,
                keepLogged: Boolean
            ): ResultState<Boolean> {
                return ResultState.NotFound
            }
        }

        val signInViewModel = SignInViewModel(signInRepository, SignInModel())

        return module {
            viewModel { signInViewModel }
        }
    }

    fun getModuleSignInFailure(): Module {
        signInRepository = object : SignInRepository {
            override suspend fun signIn(
                model: SignInModel,
                keepLogged: Boolean
            ): ResultState<Boolean> {
                return ResultState.Failure
            }
        }

        val signInViewModel = SignInViewModel(signInRepository, SignInModel())

        return module {
            viewModel { signInViewModel }
        }
    }
}
