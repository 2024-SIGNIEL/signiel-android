package com.seunghoon.generator.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.seunghoon.generator.ui.RootScreen

internal fun NavGraphBuilder.main(navController: NavController) {
    navigation(
        route = NavigationRoute.Main.route,
        startDestination = NavigationRoute.Main.ROOT,
    ) {
        composable(NavigationRoute.Main.ROOT) {
            RootScreen(navController = navController)
        }
    }
}
