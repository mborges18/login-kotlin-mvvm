package com.example.loginmvvm.common.validation

import junit.framework.TestCase
import org.junit.Test

class ValidationTest {

    @Test
    fun `verify name is valid`() {
        val name = "marcio borges".isNameValid()
        TestCase.assertEquals(true, name)
    }

    @Test
    fun `verify name is not valid`() {
        val name = "marcio".isNameValid()
        TestCase.assertEquals(false, name)
    }

    @Test
    fun `verify email is valid`() {
        val email = "marcio@gmail.com".isEmailValid()
        TestCase.assertEquals(true, email)
    }

    @Test
    fun `verify email is not valid`() {
        val email = "marcio gmail.com".isEmailValid()
        TestCase.assertEquals(false, email)
    }

    @Test
    fun `verify password is valid`() {
        val password = "123456".isPasswordValid()
        TestCase.assertEquals(true, password)
    }

    @Test
    fun `verify password is not valid`() {
        val password = "12".isPasswordValid()
        TestCase.assertEquals(false, password)
    }

    @Test
    fun `verify cell phone is valid`() {
        val phone = "81986201856".isCellPhoneValid()
        TestCase.assertEquals(true, phone)
    }

    @Test
    fun `verify cell phone is not valid`() {
        val phone = "86201853".isCellPhoneValid()
        TestCase.assertEquals(false, phone)
    }
}