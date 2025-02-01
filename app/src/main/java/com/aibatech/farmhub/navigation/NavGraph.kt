package com.aibatech.farmhub.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aibatech.farmhub.ui.auth.LoginScreen
import com.aibatech.farmhub.ui.auth.RegisterScreen
import com.aibatech.farmhub.ui.dashboard.DashboardScreen
import com.aibatech.farmhub.ui.chat.ChatListScreen
import com.aibatech.farmhub.ui.profile.ProfileScreen
import com.aibatech.farmhub.ui.navigation.BottomNavigationBar
import com.aibatech.farmhub.ui.product.AddEditProductScreen
import com.aibatech.farmhub.ui.onboarding.OnboardingScreen
import com.aibatech.farmhub.viewmodel.AuthViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = "onboarding") {
        // Onboarding Screen
        composable("onboarding") {
            OnboardingScreen(
                onGetStartedClick = { navController.navigate("login") }
            )
        }

        // Login Screen
        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                onLoginClick = { navController.navigate("main") },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        // Register Screen
        composable("register") {
            RegisterScreen(
                viewModel = authViewModel,
                onRegisterSuccess = { navController.navigate("login") }
            )
        }

        // Main Screen with Bottom Navigation
        composable("main") {
            MainScreen(navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    // Create a new NavController for the inner NavHost
    val innerNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = innerNavController) } // Bottom navigation bar uses innerNavController
    ) { innerPadding ->
        NavHost(
            navController = innerNavController, // Use the reassigned innerNavController
            startDestination = "dashboard", // Ensure the initial screen is "dashboard"
            modifier = Modifier.padding(innerPadding)
        ) {
            // Dashboard Screen
            composable("dashboard") {
                DashboardScreen()
            }


            // Add/Edit Product Screen
            composable("addEditProduct") {
                AddEditProductScreen()
            }


            // Chat Screen
            composable("chat") {
                ChatListScreen()
            }


            // Profile Screen
            composable("profile") {
                ProfileScreen(
                    onLogoutClick = {
                        navController.navigate("login") {
                            popUpTo("main") { inclusive = true } // Clear back stack when logging out
                        }
                    }
                )
            }
        }
    }
}