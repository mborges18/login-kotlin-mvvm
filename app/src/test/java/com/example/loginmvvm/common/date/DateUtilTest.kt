package com.example.loginmvvm.common.date

import com.example.loginmvvm.common.date.DateUtil.toDateAmerica
import com.example.loginmvvm.common.date.DateUtil.toDateBrazilian
import junit.framework.TestCase
import org.junit.Test

class DateUtilTest {

    @Test
    fun `converter date from brazil to america`() {
        val dateBrazil = "30/12/2023"
        val dateAmerica = dateBrazil.toDateAmerica()
        TestCase.assertEquals("2023-12-30", dateAmerica)
    }

    @Test
    fun `converter date from america do brazil`() {
        val  dateAmerica = "2023-12-30"
        val dateBrazil = dateAmerica.toDateBrazilian()
        TestCase.assertEquals("30/12/2023", dateBrazil)
    }
}