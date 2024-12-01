package com.aibatech.farmhub.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aibatech.farmhub.ui.auth.LoginScreen
import com.aibatech.farmhub.ui.auth.RegisterScreen
import com.aibatech.farmhub.ui.dashboard.DashboardScreen
import com.aibatech.farmhub.ui.navigation.BottomNavigationBar
import com.aibatech.farmhub.ui.onboarding.OnboardingScreen
import com.aibatech.farmhub.ui.order.ChatListScreen
import com.aibatech.farmhub.ui.order.ChatScreen
import com.aibatech.farmhub.ui.product.AddEditProductScreen
import com.aibatech.farmhub.ui.profile.ProfileScreen
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
                onProductClick = { productId -> navController.navigate("productDetails/$productId") },
                onOrderClick = { orderId -> navController.navigate("orderDetails/$orderId") }
            )
        }
        composable("main") {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController = navController) }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "dashboard",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    // Dashboard Screen
                    composable("dashboard") {
                        DashboardScreen(
                            onProductClick = { productId -> navController.navigate("productDetails/$productId") },
                            onOrderClick = { orderId -> navController.navigate("orderDetails/$orderId") }
                        )
                    }

                    // Add/Edit Product Screen
                    composable("addEditProduct") {
                        AddEditProductScreen()
                    }

                    // Chat List Screen
                    composable("chat") {
                        ChatListScreen(
                            onChatClick = { buyerId -> navController.navigate("chat/$buyerId") }
                        )
                    }

                    // Profile Screen
                    composable("profile") {
                        ProfileScreen()
                    }
                }
            }
        }

        // Individual Chat Screen
        composable("chat/{buyerId}") { backStackEntry ->
            val buyerId = backStackEntry.arguments?.getString("buyerId")
            ChatScreen(buyerId = buyerId)
        }
    }
}