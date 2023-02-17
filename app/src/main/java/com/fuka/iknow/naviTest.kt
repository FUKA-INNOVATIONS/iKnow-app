package com.fuka.iknow

import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.fuka.iknow.navigation.HomeScreen
import com.fuka.iknow.navigation.NavBarItems
import com.fuka.iknow.navigation.NavRoutes
import com.fuka.iknow.viewModels.DatabaseViewModel


/**
 * TODO: Poista Home teksti etusivulta.
 * TODO: Siirrää navigaatio labelit ikonien alle.
 * TODO: Pistä navigaatio toimimaan muillekkin sivuille.
 * TODO: Poista kaikki turhat tiedostot ja nimeä tämä uudelleen.
 * TODO: Poista material 1 dependency gradlesta.
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
        topBar = {
            TopAppBar(title = { Text(text = "$routeName") })
        },

        // Bottom bar uses composable NavigationBar
        bottomBar = {
            NavigationBar() {
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
            NavHost(navController = navController, startDestination = "Home") {

                navigation(startDestination = "HomeScreen", route = "Home") {
                    composable("HomeScreen") {
                        HomeScreen(viewModel = viewModel)
                    }

                    /*
                    Tähän tyyliin kun tekee sen detailed view url hausta

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
/*
                navigation(startDestination = "URLChecker", route = "URL_check") {
                    composable(NavRoutes.URLChecker.route) {
                        NavRoutes.URLChecker
                    }
                }

                navigation(startDestination = "Settings", route = "Settings") {
                    composable(NavRoutes.Settings.route) {
                        NavRoutes.Settings
                    }
                }

 */
            }
        }
    }
}