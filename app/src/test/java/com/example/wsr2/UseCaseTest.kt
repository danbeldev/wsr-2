package com.example.wsr2

import com.example.wsr2.domain.UseCases
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class UseCaseTest {

    @Test
    fun validationEmail() {
        assertFalse(UseCases.validationEmail(""))
        assertFalse(UseCases.validationEmail("test"))
        assertTrue(UseCases.validationEmail("email@bl.ru"))
    }

    @Test
    fun validationPassword() {
        assertFalse(UseCases.validationPassword(""))
        assertTrue(UseCases.validationPassword("23"))
    }

    @Test
    fun successRequest() = runBlocking {
        assertTrue(UseCases.requestLogin("dan.bel.wsr@bk.ru", null, "ggtt1234"))
    }

    @Test
    fun failedRequest() = runBlocking {
        assertFalse(UseCases.requestLogin("te@bk.ru", null, "13"))
    }
}