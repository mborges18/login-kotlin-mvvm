package com.example.loginmvvm.access.signup

import com.example.loginmvvm.access.signup.data.repository.SignUpRepository
import com.example.loginmvvm.access.signup.model.SignUpModel
import com.example.loginmvvm.access.signup.model.TypeMemberEnum
import com.example.loginmvvm.access.signup.model.TypeStatusEnum
import com.example.loginmvvm.access.signup.ui.SignUpViewModel
import com.example.loginmvvm.common.result.ResultState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object SignUpModule {

    private lateinit var signUpRepository: SignUpRepository

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
