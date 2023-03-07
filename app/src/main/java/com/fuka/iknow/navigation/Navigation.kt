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
import androidx.compose.ui.graphics.Color
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
import com.fuka.iknow.UiText
import com.fuka.iknow.screens.HomeScreen
import com.fuka.iknow.screens.SettingsScreen
import com.fuka.iknow.screens.URLCheckerScreen
import com.fuka.iknow.viewModels.DatabaseViewModel


/***
 * TODO: string resources
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage() {
    val tabItems = NavBarItems.BarItems
    var selectedItem = remember { mutableStateOf(0) }
    var navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val parentRouteName = navBackStackEntry.value?.destination?.route
    //val routeName = navBackStackEntry.value?.destination?.route

    Scaffold(
        // Bottom navigation bar.
        // Bottom bar uses composable NavigationBar.
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(75.dp)
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
                            when (barItem.title) {
                                "Events" -> Icon(
                                    Icons.Filled.List, UiText.StringResource(resId = R.string.home_screen_navigation_icon).asString(),
                                    tint = if (selectedItem.value == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )
                                "URLCheck" -> Icon(
                                    Icons.Filled.Search, UiText.StringResource(resId = R.string.url_check_screen_navigation_icon).asString(),
                                    tint = if (selectedItem.value == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )
                                "Settings" -> Icon(
                                    Icons.Filled.Settings, UiText.StringResource(resId = R.string.settings_screen_navigation_icon).asString(),
                                    tint = if (selectedItem.value == 2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )
                            }
                        },
                        label = {
                            Text(
                                text = barItem.title,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
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