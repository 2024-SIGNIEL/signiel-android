package com.seunghoon.generator.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.seunghoon.generator.feature.home.HomeScreen
import com.seunghoon.generator.ui.BottomNavigationBar

internal fun NavGraphBuilder.main(navController: NavController) {
    navigation(
        route = NavigationRoute.Main.route,
        startDestination = NavigationRoute.Main.HOME,
    ) {
        composable(NavigationRoute.Main.HOME) {
            BottomNavigationBar(navController = navController)
        }
    }
}
