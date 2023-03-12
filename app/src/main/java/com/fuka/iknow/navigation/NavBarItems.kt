package com.fuka.iknow.navigation

import com.fuka.iknow.R


// Lista buttomNavin painikkeista


object NavBarItems {

    val BarItems = listOf(
        BarItem(
            title = "Events",
            icon = R.drawable.ic_home_foreground,
            route = "events"
        ),
        BarItem(
            title = "URLCheck",
            icon = R.drawable.ic_url_check_foreground,
            route = "url_check"
        ),
        BarItem(
            title = "Settings",
            icon = R.drawable.ic_settings_foreground,
            route = "settings"
        )
    )
}