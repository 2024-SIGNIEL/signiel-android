package com.seunghoon.generator.navigation

import com.seunghoon.generator.R

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
        val HOME = this.route + "/home"
        val PAY = this.route + "/pay"
        val MY_PAGE = this.route + "/myPage"
    }
}

sealed class BottomMenu(
    val route: String,
    val icon: Int,
    val label: String,
) {
    data object HOME: BottomMenu(
        route = NavigationRoute.Root.HOME,
        icon = com.seunghoon.core.designsystem.R.drawable.ic_home,
        label = "홈",
    )

    data object PAY: BottomMenu(
        route = NavigationRoute.Root.PAY,
        icon = com.seunghoon.core.designsystem.R.drawable.ic_graph,
        label = "지출",
    )

    data object MYPAGE: BottomMenu(
        route = NavigationRoute.Root.MY_PAGE,
        icon = com.seunghoon.core.designsystem.R.drawable.ic_person,
        label = "마이페이지",
    )
}
