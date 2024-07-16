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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.seunghoon.designsystem.ui.SignielButton
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.designsystem.ui.theme.Typography
import com.seunghoon.generator.R
import com.seunghoon.generator.navigation.NavigationRoute
import kotlinx.coroutines.delay

@Composable
fun CompleteScreen(navController: NavController) {
    val animation by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash)
    )
    val lottie = rememberLottieAnimatable()

    LaunchedEffect(animation) {
        repeat(Int.MAX_VALUE) {
            lottie.animate(
                composition = animation,
                clipSpec = LottieClipSpec.Frame(0, 1200),
                initialProgress = 0f,
                reverseOnRepeat = true,
            )
            delay(3000)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .navigationBarsPadding()
            .statusBarsPadding()
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
                    append("회원가입")
                }
                withStyle(SpanStyle(color = Colors.Black)) {
                    append("이 완료되었습니다.")
                }
            },
            style = Typography.Medium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        )
        LottieAnimation(
            modifier = Modifier
                .fillMaxSize(0.6f)
                .align(Alignment.CenterHorizontally),
            composition = animation,
            progress = lottie.progress,
            contentScale = ContentScale.FillHeight,
        )
        Spacer(modifier = Modifier.weight(1f))
        SignielButton(
            text = "로그인 후 이용하기",
            onClick = {
                navController.navigate(NavigationRoute.Auth.SIGN_IN) {
                    popUpTo(0)
                }
            },
        )
    }
}