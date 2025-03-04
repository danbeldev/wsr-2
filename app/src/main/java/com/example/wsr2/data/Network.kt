package com.example.wsr2.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Network {

    @POST("/auth/v1/token")
    suspend fun login(
        @Query("grant_type") grant_type: String = "password",
        @Body body: LoginRequest
    ): Response<LoginResponse>

    @GET("/rest/v1/users")
    suspend fun getLoginAndPassword(
        @Query("username") login: String,
        @Query("password") password: String,
    ): Response<List<User>>
}


data class User(
    val id: String,
    val username: String,
    val password: String
)

val network = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://pijmzxceuhhazfnvhhie.supabase.co")
    .client(OkHttpClient.Builder().addInterceptor(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request().newBuilder().header("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBpam16eGNldWhoYXpmbnZoaGllIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDEwOTk1OTksImV4cCI6MjA1NjY3NTU5OX0.ODfp7dlCyH63XDun6VM4kFk59BkT2O-W1iVirVK1SQ0").build())
        }
    }).build())
    .build()
    .create<Network>()

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val user: LoginUser
)

data class LoginUser(
    val id: String
)