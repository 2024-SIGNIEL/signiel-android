package com.seunghoon.generator.feature.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seunghoon.designsystem.ui.SignielBoxTextField
import com.seunghoon.designsystem.ui.SignielButton
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.designsystem.ui.theme.Typography
import com.seunghoon.generator.navigation.NavigationRoute

@Composable
fun SignInScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
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
                    append("이메일")
                }
                withStyle(SpanStyle(color = Colors.Black)) {
                    append("로 로그인하세요")
                }
            },
            style = Typography.Medium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        SignInInputs(
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
        )
        Spacer(modifier = Modifier.weight(1f))
        SignielButton(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "다음",
            onClick = { navController.navigate(NavigationRoute.Main.ROOT) },
        )
    }
}

@Composable
private fun SignInInputs(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    SignielBoxTextField(
        value = email,
        onValueChange = onEmailChange,
        hint = "이메일",
    )
    Spacer(modifier = Modifier.height(36.dp))
    SignielBoxTextField(
        value = password,
        onValueChange = onPasswordChange,
        hint = "비밀번호",
        isPassword = true,
    )
}
