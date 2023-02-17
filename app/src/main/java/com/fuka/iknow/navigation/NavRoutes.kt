package com.fuka.iknow.navigation

sealed class NavRoutes(var route: String) {

    object Home: NavRoutes("home")
    object URLChecker: NavRoutes("url_check")
    object Settings: NavRoutes("settings")

    /*
    object Home: BottomNavItems("Home", R.drawable.ic_home_foreground, "home")
    object URLChecker: BottomNavItems("URLCheck", R.drawable.ic_url_check_foreground, "urlCheck")
    object Settings: BottomNavItems("Settings", R.drawable.ic_settings_foreground, "settings")
    */
}
