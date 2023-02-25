package com.fuka.iknow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fuka.iknow.screens.HomeScreen
import com.fuka.iknow.screens.SettingsScreen
import com.fuka.iknow.screens.URLCheckerScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        navigation(startDestination = "HomeScreen", route = "home") {
            composable("HomeScreen") {
                HomeScreen()
            }
        }

        navigation(startDestination = "URLCheckerScreen", route = "url_check") {
            composable("URLCheckerScreen") {
                URLCheckerScreen()
            }
        }

        navigation(startDestination = "SettingsScreen", route = "settings") {
            composable("SettingsScreen") {
                SettingsScreen()
            }
        }
    }
}