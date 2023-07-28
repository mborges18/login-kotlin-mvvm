package com.example.loginmvvm.common.string

import com.example.loginmvvm.common.string.StringUtil.handlerJustNumber
import junit.framework.TestCase
import org.junit.Test

class StringUtilTest {

    @Test
    fun `accept number only`() {
        val text = "#OKmarcio125$$%.,,10-1"
        val result = handlerJustNumber(text)
        TestCase.assertEquals("125101", result)
    }
}