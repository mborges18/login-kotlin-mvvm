package com.example.loginmvvm.access.signin.data.api

import com.example.loginmvvm.access.signin.data.model.SignInRequest
import com.example.loginmvvm.common.create
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class SignInApiTest {

    private val server = MockWebServer()
    private var api: SignInApi? = null
    private var body: SignInRequest = mockk(relaxed = true)

    @Before
    fun setup() {
        api = server.create(SignInApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `when post signIn then call url`() {
        runBlocking {
            api!!.signIn(body)
        }

        val request = server.takeRequest()
        TestCase.assertEquals("POST", request.method)
        TestCase.assertEquals("/signin", request.path)
    }
}
