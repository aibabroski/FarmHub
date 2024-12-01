package com.aibatech.farmhub.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aibatech.farmhub.R

data class BottomNavItem(
    val route: String,
    val icon: ImageVector, // Use ImageVector for default Material icons
    val title: String // Use a String instead of a resource ID for simplicity
)

val bottomNavItems = listOf(
    BottomNavItem(route = "dashboard", icon = Icons.Filled.Home, title = "Dashboard"),
    BottomNavItem(route = "addEditProduct", icon = Icons.Filled.Add, title = "Add/Edit Product"),
    BottomNavItem(route = "chat", icon = Icons.Filled.Phone, title = "Chat"),
    BottomNavItem(route = "profile", icon = Icons.Filled.Person, title = "Profile")
)
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        tonalElevation = 8.dp
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("dashboard") { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon, // Use ImageVector for default Material icons
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title) // Use plain text for titles
                }
            )
        }
    }
}
