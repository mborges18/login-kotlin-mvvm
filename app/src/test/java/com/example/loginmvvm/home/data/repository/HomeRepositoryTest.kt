package com.example.loginmvvm.home.data.repository

import com.example.loginmvvm.common.data.local.cache.Cache
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.home.data.api.HomeApi
import com.example.loginmvvm.home.data.model.CustomerResponse
import com.example.loginmvvm.home.data.model.toModel
import com.example.loginmvvm.home.model.CustomerModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class HomeRepositoryTest {

    private val api: HomeApi = mockk()
    private val cache: Cache = mockk(relaxed = true)
    private lateinit var repository: HomeRepository
    private var resultState: ResultState<CustomerModel>? =  null
    private lateinit var response: CustomerResponse

    @Before
    fun setUp() {
        coEvery { cache.get("USER_ID") } returns "1"
        repository = HomeRepositoryImpl(api, cache)
        response = CustomerResponse(
            "Marcio Borges",
            "1981-11-18",
            "mar@gor.com",
            "(81) 98620-1853",
            "GOLD"
        )
    }

    @Test
    fun `given success customer then return API result`()  = runTest {
        coEvery {
            api.getCustomer("1")
        } returns Response.success(response)

        resultState = repository.getCustomer()

        Assert.assertEquals(true, resultState is ResultState.Success)
        Assert.assertEquals(response.toModel(), (resultState as ResultState.Success).data)
        Assert.assertEquals("Marcio Borges", (resultState as ResultState.Success).data.name)
        Assert.assertEquals("18/11/1981", (resultState as ResultState.Success).data.birthdate)
        Assert.assertEquals("(81) 98620-1853", (resultState as ResultState.Success).data.phone)
        Assert.assertEquals("GOLD", (resultState as ResultState.Success).data.type)
    }

    @Test
    fun `given error customer then return API result`()  = runTest {
        coEvery {
            api.getCustomer("1")
        } returns Response.error(400, "message".toResponseBody())

        resultState = repository.getCustomer()

        Assert.assertEquals(true, resultState is ResultState.Error)
    }

    @Test
    fun `given failure customer then return API result`() = runTest {
        coEvery {
            api.getCustomer("1")
        }.throws(Throwable())

        resultState = repository.getCustomer()

        Assert.assertEquals(true, resultState is ResultState.Failure)
    }

    @Test
    fun `given logout customer then return CACHE result`() = runTest {
        coEvery { cache.get("USER_ID") } returns ""
        coEvery { cache.get("USER_KEEP_LOGGED") } returns ""

        val resultLogout = repository.logout()

        TestCase.assertEquals(true, resultLogout)
    }
}
