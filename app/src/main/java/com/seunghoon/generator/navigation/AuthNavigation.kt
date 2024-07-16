package com.seunghoon.generator.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.seunghoon.generator.SignUpData
import com.seunghoon.generator.feature.onboarding.OnBoardingScreen
import com.seunghoon.generator.feature.signin.SignInScreen
import com.seunghoon.generator.feature.signup.CompleteScreen
import com.seunghoon.generator.feature.signup.InputEmailScreen
import com.seunghoon.generator.feature.signup.InputNameScreen
import com.seunghoon.generator.feature.signup.InputPasswordScreen
import com.seunghoon.generator.feature.signup.VerifyEmailScreen
import com.seunghoon.generator.feature.splash.SplashScreen
import com.seunghoon.generator.toSignUpData

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
        composable(NavigationRoute.Auth.ON_BOARDING) {
            OnBoardingScreen(navController = navController)
        }
        composable(NavigationRoute.Auth.INPUT_NAME) {
            InputNameScreen(
                navController = navController,
                signUpData = SignUpData(),
            )
        }
        composable(
            route = NavigationRoute.Auth.INPUT_EMAIL + "/{${NavigationRoute.Auth.SIGN_UP_DATA}}",
            arguments = listOf(
                navArgument(NavigationRoute.Auth.SIGN_UP_DATA) {
                    type = NavType.StringType
                },
            ),
        ) {
            InputEmailScreen(
                navController = navController,
                signUpData = it.arguments?.getString(NavigationRoute.Auth.SIGN_UP_DATA)
                    ?.toSignUpData() ?: SignUpData(),
            )
        }
        composable(
            route = NavigationRoute.Auth.VERIFY_EMAIL + "/{${NavigationRoute.Auth.SIGN_UP_DATA}}",
            arguments = listOf(
                navArgument(NavigationRoute.Auth.SIGN_UP_DATA) {
                    type = NavType.StringType
                },
            )
        ) {
            VerifyEmailScreen(
                navController = navController,
                signUpData = it.arguments?.getString(NavigationRoute.Auth.SIGN_UP_DATA)
                    ?.toSignUpData() ?: SignUpData(),
            )
        }
        composable(
            route = NavigationRoute.Auth.INPUT_PASSWORD + "/{${NavigationRoute.Auth.SIGN_UP_DATA}}",
            arguments = listOf(
                navArgument(NavigationRoute.Auth.SIGN_UP_DATA) {
                    type = NavType.StringType
                },
            )
        ) {
            InputPasswordScreen(
                navController = navController,
                signUpData = it.arguments?.getString(NavigationRoute.Auth.SIGN_UP_DATA)
                    ?.toSignUpData() ?: SignUpData(),
            )
        }
        composable(
            route = NavigationRoute.Auth.COMPLETE,
        ) {
            CompleteScreen(navController = navController)
        }
    }
}

