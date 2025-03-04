package com.example.wsr2.domain

import com.example.wsr2.data.LoginRequest
import com.example.wsr2.data.network

object UseCases {

    suspend fun requestLogin(
        email: String?,
        login: String?,
        password: String,
        onSuccess: (String) -> Unit = {}
    ): Boolean {
        if (!validationPassword(password)) {
            return false
        }

        if (login != null) {
            val response = network.getLoginAndPassword(login, password)
            if (response.isSuccessful) {
                response.body()?.firstOrNull()?.let {
                    onSuccess(it.id)
                }
            }
            return response.isSuccessful
        }else if (email != null){
            if (!validationEmail(email)) {
                return false
            }
            val response = network.login(body = LoginRequest(email, password))
            response.body()?.let {
                onSuccess(it.user.id)
            }
            return response.isSuccessful
        }else throw Exception()
    }

    fun validationEmail(email: String): Boolean {
        if (email.length == 0)
            return false

        val a = email.split("@")
        if (a.size < 2)
            return false

        if (!a[0].all { it.isLowerCase() || it.isDigit() || it == '.' })
            return false

        val b = a[1].split(".")
        if (b.size < 2)
            return false

        if (b[1].length < 2)
            return false

        return true
    }

    fun validationPassword(password: String): Boolean {
        if (password.length == 0)
            return false
        else
            return true
    }
}