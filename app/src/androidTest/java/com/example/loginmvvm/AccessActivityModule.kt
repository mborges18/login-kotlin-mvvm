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
import io.mockk.coEvery
import io.mockk.mockk
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AccessActivityModule {

    private var signUpRepository: SignUpRepository = mockk()
    private lateinit var signUpModel: SignUpModel

    private var signInRepository: SignInRepository = mockk()
    private lateinit var signInModel: SignInModel

    fun getModuleSignUpSuccess(): Module {
        setSignUpModel()

        val result: ResultState<SignUpModel> = ResultState.Success(signUpModel)
        coEvery { signUpRepository.signUp(model = signUpModel) } returns result

        return module {
            viewModel { SignUpViewModel(signUpRepository, signUpModel) }
        }
    }

    fun getModuleSignInSuccess(): Module {
        setSignInModel()

        val result: ResultState<Boolean> = ResultState.Success(true)
        coEvery { signInRepository.signIn(model = signInModel, keepLogged = true) } returns result

        return module {
            viewModel { SignInViewModel(signInRepository, signInModel) }
        }
    }

    private fun setSignUpModel() {
        signUpModel = SignUpModel(
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

    private fun setSignInModel() {
        signInModel = SignInModel(
            email = "A@123456",
            password = "marcioorges18@gmail.com"
        )
    }
}
