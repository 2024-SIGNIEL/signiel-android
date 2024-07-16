package com.seunghoon.generator.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.seunghoon.generator.feature.signin.SignInScreen
import com.seunghoon.generator.feature.splash.SplashScreen

internal fun NavGraphBuilder.auth(navController: NavController) {
    navigation(
        route = NavigationRoute.Auth.route,
        startDestination = NavigationRoute.Auth.SPLASH,
    ) {
        composable(NavigationRoute.Auth.SPLASH) {
            SplashScreen(
                navController = navController,
            )
        }
        composable(NavigationRoute.Auth.SIGN_IN) {
            SignInScreen(navController = navController)
        }
    }
}
