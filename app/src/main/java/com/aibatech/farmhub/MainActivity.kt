package com.aibatech.farmhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.aibatech.farmhub.ui.theme.FarmHubTheme
import com.aibatech.farmhub.navigation.NavGraph
import com.aibatech.farmhub.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FarmHubTheme {
                val navController = rememberNavController() // Initialize NavController
                val authViewModel: AuthViewModel = viewModel() // ViewModel for authentication
                NavGraph(navController = navController, authViewModel = authViewModel)
            }
        }
    }
}



