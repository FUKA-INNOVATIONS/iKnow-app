package com.fuka.iknow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.fuka.iknow.navigation.NavBarItems
import com.fuka.iknow.screens.HomeScreen
import com.fuka.iknow.screens.SettingsScreen
import com.fuka.iknow.screens.URLCheckerScreen
import com.fuka.iknow.viewModels.DatabaseViewModel


/**
 * TODO: Raise navigation icons above the navigation labels.
 * TODO: Implement stack navigation to API screen.
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage(viewModel: DatabaseViewModel) {
    val tabItems = NavBarItems.BarItems

    var selectedItem = remember { mutableStateOf(0) }

    var navController = rememberNavController()

    val navBackStackEntry = navController.currentBackStackEntryAsState()

    val parentRouteName = navBackStackEntry.value?.destination?.route

    val routeName = navBackStackEntry.value?.destination?.route

    Scaffold(
        // Top app bar.
        // In this file if navigation is added in the future.
        topBar = {
            TopAppBar(title = { Text(text = "$routeName") })
        },

        // Bottom navigation bar.
        // Bottom bar uses composable NavigationBar.
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(65.dp)
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {
                tabItems.forEachIndexed { index, barItem ->

                NavigationBarItem(
                    selected = parentRouteName == barItem.route,
                    onClick = {
                        selectedItem.value = index
                        navController.navigate(barItem.route, navOptions {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        })
                    },
                    icon = {
                        Box(
                            modifier = Modifier
                                .height(800.dp)
                        ) {
                            when (barItem.title) {
                                "Home" -> Icon(
                                    painter = painterResource(id = R.drawable.ic_home_foreground),
                                    contentDescription = null
                                )
                                "URLCheck" -> Icon(
                                    painter = painterResource(id = R.drawable.ic_url_check_foreground),
                                    contentDescription = null
                                )
                                "Settings" -> Icon(
                                    painter = painterResource(id = R.drawable.ic_settings_foreground),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    label = { Text(text = barItem.title) }
                )
            }
        }
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            NavHost(navController = navController, startDestination = "home") {

                navigation(startDestination = "HomeScreen", route = "home") {
                    composable("HomeScreen") {
                        HomeScreen(viewModel = viewModel)
                    }
                }

                navigation(startDestination = "URLCheckerScreen", route = "url_check") {
                    composable("URLCheckerScreen") {
                        URLCheckerScreen()
                    }
                    /*
                    Tähän tyyliin kun tekee sen detailed view

                    composable("HomePage" , deepLinks = listOf(NavDeepLink("deeplink://home"))) {
                        HomePage(navController = navController)
                    }
                    // Add another route in Home nested navigation.
                    // Now Create for Another Composable
                    composable("HomeDetailPage",deepLinks = listOf(NavDeepLink("deeplink://homeDetail")) ) {
                        HomeDetailPage(navController = navController)
                    }
                    */
                }

                navigation(startDestination = "SettingsScreen", route = "settings") {
                    composable("SettingsScreen") {
                        SettingsScreen()
                    }
                }
            }
        }
    }
}