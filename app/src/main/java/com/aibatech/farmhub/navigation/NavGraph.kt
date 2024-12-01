package com.aibatech.farmhub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aibatech.farmhub.ui.auth.LoginScreen
import com.aibatech.farmhub.ui.auth.RegisterScreen
import com.aibatech.farmhub.ui.dashboard.DashboardScreen
import com.aibatech.farmhub.ui.onboarding.OnboardingScreen
import com.aibatech.farmhub.viewmodel.AuthViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel // Inject the ViewModel from a higher level
) {
    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnboardingScreen {
                navController.navigate("login")
            }
        }
        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                onLoginClick = { navController.navigate("dashboard") },
                onRegisterClick = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen(
                viewModel = authViewModel,
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true } // Prevents going back to register screen
                    }
                }
            )
        }
        composable("dashboard") {
            DashboardScreen(
                onAddProductClick = { navController.navigate("addProduct") },
                onViewOrdersClick = { navController.navigate("orders") },
                onProductClick = { productId ->
                    navController.navigate("editProduct/$productId")
                }
            )
        }
    }
}