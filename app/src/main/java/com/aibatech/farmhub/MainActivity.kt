package com.aibatech.farmhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.aibatech.farmhub.navigation.NavGraph
import com.aibatech.farmhub.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Create the AuthViewModel instance
            val authViewModel: AuthViewModel = viewModel()

            // Set up navigation with the ViewModel
            val navController = rememberNavController()
            NavGraph(navController = navController, authViewModel = authViewModel)
        }
    }
}

