package com.example.loginmvvm.access.signin.data.repository

import com.example.loginmvvm.access.signin.data.api.SignInApi
import com.example.loginmvvm.access.signin.data.model.SignInRequest
import com.example.loginmvvm.access.signin.data.model.SignInResponse
import com.example.loginmvvm.access.signin.model.SignInModel
import com.example.loginmvvm.common.data.local.cache.Cache
import com.example.loginmvvm.common.result.ResultState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class SignInRepositoryTest {

    private val api: SignInApi = mockk(relaxed = true)
    private val cache: Cache = mockk(relaxed = true)
    private val repository: SignInRepository = SignInRepositoryImpl(api, cache)
    private var resultState: ResultState<Boolean>? =  null
    private val signInRequest = SignInRequest("email", "password")
    private val signInResponse = SignInResponse("123")
    private val signInModel = SignInModel("email", "password")

    @Test
    fun `given success signIn then return API result`() {
        coEvery {
            api.signIn(signInRequest)
        } returns Response.success(signInResponse)

        runBlocking {
            resultState = repository.signIn(signInModel, true)
        }

        Assert.assertEquals(true, resultState is ResultState.Success)
        Assert.assertEquals(true, (resultState as ResultState.Success).data)
    }

    @Test
    fun `given not found signIn then return API result`() {
        coEvery {
            api.signIn(signInRequest)
        } returns Response.error(404, "message".toResponseBody())

        runBlocking {
            resultState = repository.signIn(signInModel, true)
        }

        Assert.assertEquals(true, resultState is ResultState.NotFound)
    }

    @Test
    fun `given error signIn then return API result`() {
        coEvery {
            api.signIn(signInRequest)
        } returns Response.error(400, "message".toResponseBody())

        runBlocking {
            resultState = repository.signIn(signInModel, true)
        }

        Assert.assertEquals(true, resultState is ResultState.Error)
    }
}
