package com.aibatech.farmhub.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.aibatech.farmhub.ui.theme.AppTypography
import com.aibatech.farmhub.viewmodel.AuthState
import com.aibatech.farmhub.viewmodel.AuthViewModel
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults



@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
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
            style = MaterialTheme.typography.displayMedium.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Email Input
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email", style = MaterialTheme.typography.bodyMedium) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )


        // Password Input
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password", style = MaterialTheme.typography.bodyMedium) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )


        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = { viewModel.login(email.value, password.value) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.labelLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Authentication State
        when (authState.value) {
            is AuthState.Loading -> Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            is AuthState.Success -> {
                Text(
                    text = "Login Successful!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                LaunchedEffect(Unit) {
                    onLoginClick()
                    viewModel.resetState() // Reset state to prevent re-trigger
                }
            }
            is AuthState.Error -> Text(
                text = (authState.value as AuthState.Error).message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
            else -> {}
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register Redirect
        Text(
            text = "Don't have an account? Register",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onRegisterClick() }
        )
    }
}
