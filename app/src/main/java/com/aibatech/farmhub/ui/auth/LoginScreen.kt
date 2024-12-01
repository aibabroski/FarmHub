package com.aibatech.farmhub.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aibatech.farmhub.ui.theme.poppins
import com.aibatech.farmhub.viewmodel.AuthState
import com.aibatech.farmhub.viewmodel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel, onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val authState = viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Login to FarmHub",
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Email Input
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email", fontFamily = poppins) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Input
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password", fontFamily = poppins) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = { viewModel.login(email.value, password.value) },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Login",
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Handle Authentication State
        when (authState.value) {
            is AuthState.Loading -> Text(
                text = "Loading...",
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            is AuthState.Success -> {
                Text(
                    text = "Login Successful!",
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 8.dp)
                )
                onLoginClick()
            }
            is AuthState.Error -> Text(
                text = "Error: ${(authState.value as AuthState.Error).message}",
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
            else -> {}
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register Redirect
        Text(
            text = "Don't have an account? Register",
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onRegisterClick() }
        )
    }
}
