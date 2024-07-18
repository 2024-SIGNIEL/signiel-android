package com.seunghoon.generator.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.generator.feature.home.HomeScreen
import com.seunghoon.generator.feature.home.PayScreen
import com.seunghoon.generator.feature.my.MyPageScreen
import com.seunghoon.generator.navigation.BottomMenu
import com.seunghoon.generator.navigation.NavigationRoute

val menus = listOf(
    BottomMenu.PAY,
    BottomMenu.HOME,
    BottomMenu.MYPAGE,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootScreen(
    navController: NavController,
) {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = Color.White) {
                val selectedRoute =
                    navHostController.currentBackStackEntryAsState().value?.destination?.route
                menus.forEach {
                    BottomNavigationItem(
                        selectedContentColor = Colors.Main,
                        unselectedContentColor = Color.Black,
                        selected = selectedRoute == it.route,
                        onClick = {
                            navHostController.navigate(it.route) {
                                popUpTo(0) {
                                    inclusive = true
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Column {
                                Spacer(modifier = Modifier.height(12.dp))
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(it.icon),
                                    contentDescription = null,
                                    tint = if (selectedRoute == it.route) Colors.Main else Color.Black
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        },
                        label = {
                            Text(
                                modifier = Modifier.navigationBarsPadding(),
                                text = it.label,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = if (selectedRoute == it.route) Colors.Main else Color.Black,
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            navController = navHostController,
            startDestination = NavigationRoute.Root.HOME,
        ) {
            composable(NavigationRoute.Root.HOME) {
                HomeScreen(
                    navHostController = navHostController,
                    navController = navController,
                )
            }
            composable(NavigationRoute.Root.PAY) {
                PayScreen(navController = navHostController)
            }
            composable(NavigationRoute.Root.MY_PAGE) {
                MyPageScreen(navController = navController)
            }
        }
    }
}
