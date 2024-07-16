package com.seunghoon.generator.feature.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.designsystem.ui.theme.Typography

@Composable
fun SignInScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Colors.Main)) {
                    append("Fino")
                }
                withStyle(SpanStyle(color = Colors.Black)) {
                    append(" 로그인")
                }
            },
            style = Typography.Medium.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Colors.Main)) {
                    append("Fino")
                }
                withStyle(SpanStyle(color = Colors.Black)) {
                    append("에 로그인하세요")
                }
            },
            style = Typography.Medium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        )
    }
}
