package com.example.loginmvvm

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

object AccessActivityRobot {

    private var signUpRepository: SignUpRepository = mockk()
    private lateinit var signUpModel: SignUpModel

    fun getModuleData(): Module {
        signUpModel = SignUpModel(
            name = "Marcio Borges",
            birthDate = "18/11/1981",
            email = "email@test.com",
            phone = "(81) 986201589",
            typeMember = TypeMemberEnum.GOLD,
            status = TypeStatusEnum.ACTIVE,
            password = "A@123456",
            confirmPassword = "A@123456"
        )
        val result: ResultState<SignUpModel> = ResultState.Success(signUpModel)
        coEvery { signUpRepository.signUp(model = signUpModel) } returns result

        return module {
            viewModel { SignUpViewModel(signUpRepository, signUpModel) }
        }
    }
}