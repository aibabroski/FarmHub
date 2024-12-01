package com.aibatech.farmhub.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aibatech.farmhub.viewmodel.AuthState
import com.aibatech.farmhub.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(viewModel: AuthViewModel, onRegisterSuccess: () -> Unit) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Form Fields
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val farmName = remember { mutableStateOf("") }
    val farmSize = remember { mutableStateOf("") }
    val location = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    val authState by viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Farmer Register",
            fontSize = 28.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input Fields
        TextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = farmName.value,
            onValueChange = { farmName.value = it },
            label = { Text("Farm Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = farmSize.value,
            onValueChange = { farmSize.value = it },
            label = { Text("Farm Size") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = location.value,
            onValueChange = { location.value = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Register Button
        Button(
            onClick = {
                when {
                    firstName.value.isBlank() || lastName.value.isBlank() || email.value.isBlank() ||
                            farmName.value.isBlank() || farmSize.value.isBlank() || location.value.isBlank() ||
                            password.value.isBlank() || confirmPassword.value.isBlank() -> {
                        Toast.makeText(context, "All fields are required.", Toast.LENGTH_SHORT).show()
                    }
                    password.value != confirmPassword.value -> {
                        Toast.makeText(context, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        coroutineScope.launch {
                            viewModel.register(
                                firstName = firstName.value,
                                lastName = lastName.value,
                                email = email.value,
                                farmName = farmName.value,
                                farmSize = farmSize.value,
                                location = location.value,
                                password = password.value
                            )
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Handle Authentication State
        when (authState) {
            is AuthState.Loading -> Text("Registering...")
            is AuthState.Success -> {
                Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
                viewModel.resetState() // Reset state
                onRegisterSuccess()
            }
            is AuthState.Error -> {
                val errorMessage = (authState as AuthState.Error).message
                Toast.makeText(context, errorMessage ?: "An error occurred.", Toast.LENGTH_SHORT).show()
                viewModel.resetState() // Reset state after showing error
            }
            else -> {}
        }
    }
}
