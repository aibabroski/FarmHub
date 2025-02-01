package com.aibatech.farmhub.data.repository

import com.aibatech.farmhub.data.network.AuthApiService
import com.aibatech.farmhub.data.network.AuthResponse
import com.aibatech.farmhub.data.network.RetrofitClient
import com.aibatech.farmhub.data.network.RegisterRequest
import com.aibatech.farmhub.data.network.LoginRequest
import retrofit2.Response

class AuthRepository(private val apiService: AuthApiService = RetrofitClient.instance) {

    // Updated register function to include all required fields
    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        farmName: String,
        farmSize: String,
        location: String,
        password: String
    ): Response<AuthResponse> {
        val request = RegisterRequest(
            first_name = firstName,
            last_name = lastName,
            email = email,
            farm_name = farmName,
            farm_size = farmSize,
            location = location,
            password = password
        )
        println("Register Request: $request")
        val response = apiService.register(request)
        println("Register Response: ${response.body()}") // Log the response
        return response
    }


    suspend fun login(email: String, password: String): Response<AuthResponse> {
        val request = LoginRequest(email, password)
        println("Login Request: $request") // Log the request
        val response = apiService.login(request)
        println("Login Response: ${response.body()}") // Log the response
        return response
    }


}


