package com.example.loginmvvm.home.data.api

import com.example.loginmvvm.common.create
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeApiTest {

    private val server = MockWebServer()
    private var api: HomeApi? = null

    @Before
    fun setup() {
        api = server.create(HomeApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `when get data customer then call url`() {
        runBlocking {
            api!!.getCustomer("1")
        }

        val request = server.takeRequest()
        TestCase.assertEquals("GET", request.method)
        TestCase.assertEquals("/customer", request.path)
    }
}
