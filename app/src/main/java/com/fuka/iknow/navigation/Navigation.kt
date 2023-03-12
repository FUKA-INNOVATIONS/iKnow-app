package com.fuka.iknow.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.fuka.iknow.R
import androidx.compose.ui.res.stringResource




// Navigointi logiikka
// Aktiivina olevan näkymän ikonin väri indiköt missä näkymässä käyttäjä sijaitsee


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
                                stringResource(R.string.events_tab) -> Icon(
                                    Icons.Filled.List, stringResource(R.string.home_screen_navigation_icon),
                                    tint = if (selectedItem.value == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )
                                stringResource(R.string.URLCheck_tab) -> Icon(
                                    Icons.Filled.Search, stringResource(R.string.url_check_screen_navigation_icon),
                                    tint = if (selectedItem.value == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )
                                stringResource(R.string.settings_tab) -> Icon(
                                    Icons.Filled.Settings, stringResource(R.string.settings_screen_navigation_icon),
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