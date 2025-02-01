package com.aibatech.farmhub.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class RegisterRequest(
    val first_name: String,
    val last_name: String,
    val email: String,
    val farm_name: String,
    val farm_size: String,
    val location: String,
    val password: String
)

data class LoginRequest(val email: String, val password: String)

data class AuthResponse(
    val success: Boolean, // Indicates whether the login was successful
    val token: String?,   // Optional, can be null
    val message: String?  // Message describing the result
)

interface AuthApiService {

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

}
