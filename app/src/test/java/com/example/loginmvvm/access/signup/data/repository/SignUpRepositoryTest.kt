package com.example.loginmvvm.access.signup.data.repository

import com.example.loginmvvm.access.signup.data.api.SignUpApi
import com.example.loginmvvm.access.signup.data.model.SignUpRequest
import com.example.loginmvvm.access.signup.data.model.SignUpResponse
import com.example.loginmvvm.access.signup.domain.SignUpModel
import com.example.loginmvvm.access.signup.domain.TypeMemberEnum
import com.example.loginmvvm.access.signup.domain.TypeStatusEnum
import com.example.loginmvvm.access.signup.domain.toRequest
import com.example.loginmvvm.common.isNotNull
import com.example.loginmvvm.common.result.ResultState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class SignUpRepositoryTest {

    private val api: SignUpApi = mockk(relaxed = true)
    private val repository: SignUpRepository = SignUpRepositoryImpl(api)
    private var resultState: ResultState<SignUpModel>? =  null
    private lateinit var signUpRequest: SignUpRequest
    private lateinit var signUpResponse: SignUpResponse
    private lateinit var signUpModel: SignUpModel

    @Before
    fun setUp() {
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

        signUpRequest = signUpModel.toRequest()

        signUpResponse = SignUpResponse(
            id = "1",
            modifiedTime = "2023-06-03 10:45:37:530",
            createdTime = "2022-10-26 13:45:08:875",
            name = "Marcio Borges",
            phone = "81986201853",
            birthDate = "1981-11-18",
            email = "email@test.com",
            typeMember = TypeMemberEnum.GOLD,
            status = TypeStatusEnum.ACTIVE
        )
    }

    @Test
    fun `given success signUp then return API result`() {
        coEvery {
            api.signUp(signUpRequest)
        } returns Response.success(Unit)

        runBlocking {
            resultState = repository.signUp(signUpModel)
        }

        Assert.assertEquals(true, resultState is ResultState.Success)
        Assert.assertEquals(true, (resultState as ResultState.Success).data.isNotNull())
    }

    @Test
    fun `given already exists signUp then return API result`() {
        coEvery {
            api.signUp(signUpRequest)
        } returns Response.error(409, "message".toResponseBody())

        runBlocking {
            resultState = repository.signUp(signUpModel)
        }

        Assert.assertEquals(true, resultState is ResultState.Exists)
    }

    @Test
    fun `given error signUp then return API result`() {
        coEvery {
            api.signUp(signUpRequest)
        } returns Response.error(400, "message".toResponseBody())

        runBlocking {
            resultState = repository.signUp(signUpModel)
        }

        Assert.assertEquals(true, resultState is ResultState.Error)
    }
}
