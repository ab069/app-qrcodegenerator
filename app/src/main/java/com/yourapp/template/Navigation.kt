package com.yourapp.template

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourapp.template.ui.screens.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    // Add more screens here: object Detail : Screen("detail/{id}")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        // Add more destinations here
    }
}
