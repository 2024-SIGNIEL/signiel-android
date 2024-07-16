package com.seunghoon.generator.feature.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seunghoon.designsystem.ui.SignielButton
import com.seunghoon.designsystem.ui.SignielTextField
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.designsystem.ui.theme.Typography
import com.seunghoon.generator.SignUpData
import com.seunghoon.generator.navigation.NavigationRoute
import com.seunghoon.generator.toJsonString

@Composable
fun InputNameScreen(
    navController: NavController,
    signUpData: SignUpData,
) {
    val (name, onNameChange) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(horizontal = 30.dp),
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Colors.Main)) {
                    append("Fino")
                }
                withStyle(SpanStyle(color = Colors.Black)) {
                    append(" 회원가입")
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
                    append("이름")
                }
                withStyle(SpanStyle(color = Colors.Black)) {
                    append("을 입력하세요")
                }
            },
            style = Typography.Medium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        SignielTextField(
            value = name,
            hint = "이름을 입력하세요",
            onValueChange = onNameChange,
        )
        Spacer(modifier = Modifier.weight(1f))
        SignielButton(
            text = "다음",
            onClick = {
                navController.navigate(
                    NavigationRoute.Auth.INPUT_EMAIL + "/${
                        signUpData.copy(
                            name = name,
                        ).toJsonString()
                    }"
                )
            },
        )
    }
}
