package com.sennohananto.footballmatchschedule

import org.junit.Assert
import org.junit.Test

class UtilsKtTest {

    @Test
    fun toDateIndo() {
        val unformatted = "01/10/18"
        Assert.assertEquals("Sen, 01 Okt 2018",com.sennohananto.footballmatchschedule.toDateIndo(unformatted,"dd/MM/yy"))
    }
}