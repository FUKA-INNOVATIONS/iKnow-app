package com.fuka.iknow.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import androidx.navigation.navigation
import com.fuka.iknow.R
import com.fuka.iknow.screens.HomeScreen
import com.fuka.iknow.screens.SettingsScreen
import com.fuka.iknow.screens.URLCheckerScreen
import com.fuka.iknow.viewModels.DatabaseViewModel


/**
 * TODO: Raise navigation icons above the navigation labels.
 * TODO: Implement stack navigation to API screen.
 */

// TODO: fix material theme reference, dependencies, split code into separate files...

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage() {
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
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "$routeName",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                /*navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },*/
                /*actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                }*/
            )
        },

        // Bottom navigation bar.
        // Bottom bar uses composable NavigationBar.
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(75.dp)
                    .background(color = MaterialTheme.colorScheme.secondary) // TODO: fix material theme reference
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
                            when (barItem.title) {
                                "Events" -> Icon(Icons.Filled.Home, "Home screen navigation icon")
                                "URLCheck" -> Icon(Icons.Filled.Search, "Search screen navigation icon")
                                "Settings" -> Icon(Icons.Filled.Settings, "Settings navigation icon")
                            }
                        },
                        label = { Text(text = barItem.title, maxLines = 1, overflow = TextOverflow.Ellipsis) } //
                    )
                }
            }
        }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            AppNavigation(navController = navController)
        }
    }
}