package com.sennohananto.footballmatchschedule

import org.junit.Assert
import org.junit.Test

class UtilsKtTest {

    @Test
    fun toDateIndo() {
        //2018-10-20
        val unformatted = "2018-10-01"
        Assert.assertEquals("Sen, 01 Okt 2018",com.sennohananto.footballmatchschedule.toDateIndo(unformatted,"yyyy-MM-dd"))
    }

    @Test
    fun toHourIndo() {
        //Retrieved date format in GMT from thesportsdb
        val hourInGMT = "21:00:00+00:00"

        //21:00 in GMT is 04:00 in Jakarta (GMT +7)
        Assert.assertEquals("04:00",com.sennohananto.footballmatchschedule.toHourIndo(hourInGMT))
    }
}