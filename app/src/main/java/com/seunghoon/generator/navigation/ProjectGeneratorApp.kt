package com.seunghoon.generator.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
internal fun ProjectGeneratorApp() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = NavigationRoute.Auth.route,
    ) {
        auth(navController = navController)
        main(navController = navController)
    }
}
