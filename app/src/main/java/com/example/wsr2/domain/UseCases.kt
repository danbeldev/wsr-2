package com.example.wsr2.domain

object UseCases {

    suspend fun requestLogin(
        email: String?,
        login: String?,
        password: String
    ): Boolean {
        throw Exception()
    }

    fun validationEmail(email: String): Boolean {
        throw Exception()
    }

    fun validationPassword(password: String): Boolean {
        throw Exception()
    }
}