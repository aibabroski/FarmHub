package com.aibatech.farmhub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibatech.farmhub.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Success(val token: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        farmName: String,
        farmSize: String,
        location: String,
        password: String
    ) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                // Make API call
                val response = repository.register(
                    firstName, lastName, email, farmName, farmSize, location, password
                )

                if (response.isSuccessful && response.body() != null) {
                    // Success case
                    _authState.value = AuthState.Success("Registration successful.")
                } else {
                    // Handle API errors
                    val errorMessage = response.errorBody()?.string() ?: "Registration failed."
                    _authState.value = AuthState.Error(errorMessage)
                }
            } catch (e: Exception) {
                // Handle network or other unexpected errors
                _authState.value = AuthState.Error(e.message ?: "An unexpected error occurred.")
            }
        }
    }


    fun login(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                if (response.isSuccessful && response.body()?.success == true) {
                    _authState.value = AuthState.Success("Login successful!")
                } else {
                    _authState.value = AuthState.Error("Invalid credentials.")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "An unexpected error occurred.")
            }
        }
    }


    fun resetState() {
        _authState.value = AuthState.Idle
    }
}