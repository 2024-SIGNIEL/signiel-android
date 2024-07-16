package com.seunghoon.generator.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.seunghoon.core.designsystem.R
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.generator.navigation.NavigationRoute
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(NavigationRoute.Auth.ON_BOARDING)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.Main),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_title),
            contentDescription = null,
        )
        Image(
            modifier = Modifier.align(Alignment.BottomStart),
            painter = painterResource(id = R.drawable.ic_bubble),
            contentDescription = null,
        )
    }
}
