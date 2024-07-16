package com.seunghoon.generator.navigation

sealed class NavigationRoute(val route: String) {
    data object Auth : NavigationRoute("auth") {
        val SPLASH = this.route + "/splash"
        val SIGN_IN = this.route + "/signIn"
        val INPUT_NAME = this.route + "/inputName"
        val ON_BOARDING = this.route + "/onBoarding"
        val INPUT_EMAIL = this.route + "/inputEmail"
        val VERIFY_EMAIL = this.route + "/verifyEmail"
        val INPUT_PASSWORD = this.route + "/inputPassword"
        val COMPLETE = this.route + "/complete"
        val SIGN_UP_DATA = "signUpData"
    }

    data object Main : NavigationRoute("main") {
        val ROOT = this.route + "/root"
    }

    data object Root : NavigationRoute("root") {

    }
}
