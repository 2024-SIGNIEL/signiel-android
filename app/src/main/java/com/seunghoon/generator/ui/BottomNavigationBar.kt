package com.seunghoon.generator.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seunghoon.core.designsystem.R
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.generator.feature.home.HomeScreen

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                BottomNavigationItem(
                    selectedContentColor = Colors.Main,
                    unselectedContentColor = Color.Black,
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    icon = {
                        Column {
                            Spacer(modifier = Modifier.height(12.dp))
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(R.drawable.ic_graph),
                                contentDescription = null,
                                tint = if (selectedItem == 1) Colors.Main else Color.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    },
                    label = {
                        Text(
                            text = "지출",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = if (selectedItem == 1) Colors.Main else Color.Black,
                        )
                    }
                )
                BottomNavigationItem(
                    selectedContentColor = Colors.Main,
                    unselectedContentColor = Color.Black,
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = {
                        Column {
                            Spacer(modifier = Modifier.height(12.dp))
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(R.drawable.ic_home),
                                contentDescription = null,
                                tint = if (selectedItem == 0) Colors.Main else Color.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    },
                    label = {
                        Text(
                            text = "홈",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = if (selectedItem == 0) Colors.Main else Color.Black,
                        )
                    }
                )
                BottomNavigationItem(
                    selectedContentColor = Colors.Main,
                    unselectedContentColor = Color.Black,
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = {
                        Column {
                            Spacer(modifier = Modifier.height(12.dp))
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(R.drawable.ic_person),
                                contentDescription = null,
                                tint = if (selectedItem == 2) Colors.Main else Color.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    },
                    label = {
                        Text(
                            text = "마이페이지",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = if (selectedItem == 2) Colors.Main else Color.Black
                        )
                    }
                )
            }
        }
    ) { paddingValues ->
        when (selectedItem) {
            0 -> HomeScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )

            /*1 -> SearchScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
            )

            2 -> MyPageScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
            )*/
        }
    }
}