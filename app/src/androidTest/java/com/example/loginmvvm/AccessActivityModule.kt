package com.example.loginmvvm

import com.example.loginmvvm.access.signin.data.repository.SignInRepository
import com.example.loginmvvm.access.signin.model.SignInModel
import com.example.loginmvvm.access.signin.ui.SignInViewModel
import com.example.loginmvvm.access.signup.data.repository.SignUpRepository
import com.example.loginmvvm.access.signup.model.SignUpModel
import com.example.loginmvvm.access.signup.model.TypeMemberEnum
import com.example.loginmvvm.access.signup.model.TypeStatusEnum
import com.example.loginmvvm.access.signup.ui.SignUpViewModel
import com.example.loginmvvm.common.result.ResultState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AccessActivityModule {

    private lateinit var signUpRepository: SignUpRepository
    private lateinit var signInRepository: SignInRepository

    fun getModuleSignUpSuccess(): Module {
        signUpRepository = object : SignUpRepository {
            override suspend fun signUp(model: SignUpModel): ResultState<SignUpModel> {
                return ResultState.Success(getSignUpModel())
            }
        }

        return module {
            viewModel { SignUpViewModel(signUpRepository, SignUpModel()) }
        }
    }

    fun getModuleSignUpExists(): Module {
        signUpRepository = object : SignUpRepository {
            override suspend fun signUp(model: SignUpModel): ResultState<SignUpModel> {
                return ResultState.Exists
            }
        }

        return module {
            viewModel { SignUpViewModel(signUpRepository, SignUpModel()) }
        }
    }

    fun getModuleSignUpError(): Module {
        signUpRepository = object : SignUpRepository {
            override suspend fun signUp(model: SignUpModel): ResultState<SignUpModel> {
                return ResultState.Error
            }
        }

        return module {
            viewModel { SignUpViewModel(signUpRepository, SignUpModel()) }
        }
    }

    fun getModuleSignUpFailure(): Module {
        signUpRepository = object : SignUpRepository {
            override suspend fun signUp(model: SignUpModel): ResultState<SignUpModel> {
                return ResultState.Failure
            }
        }

        return module {
            viewModel { SignUpViewModel(signUpRepository, SignUpModel()) }
        }
    }

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

    private fun getSignUpModel() = SignUpModel(
            name = "Marcio Borges",
            birthDate = "18/11/1981",
            email = "marcioorges18@gmail.com",
            phone = "(81) 986201589",
            typeMember = TypeMemberEnum.GOLD,
            status = TypeStatusEnum.ACTIVE,
            password = "A@123456",
            confirmPassword = "A@123456"
        )
}
