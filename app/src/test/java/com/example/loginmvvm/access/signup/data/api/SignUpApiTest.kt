package com.example.loginmvvm.access.signup.data.api

import com.example.loginmvvm.access.signup.data.model.SignUpRequest
import com.example.loginmvvm.common.create
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class SignUpApiTest {

    private val server = MockWebServer()
    private var api: SignUpApi? = null
    private var body: SignUpRequest = mockk(relaxed = true)

    @Before
    fun setup() {
        api = server.create(SignUpApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `when post signUp then call url`() {
        runBlocking {
            api!!.signUp(body)
        }

        val request = server.takeRequest()
        TestCase.assertEquals("POST", request.method)
        TestCase.assertEquals("/signup", request.path)
    }
}