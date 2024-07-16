package com.seunghoon.designsystem.ui.theme

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 디자인에 맞는 폰트 추가해서 사용
private val FontFamily = FontFamily(listOf())

private val platFormTextStyle = PlatformTextStyle(
    includeFontPadding = false,
)

// 이름 변경해서 사용
object Typography {
    /*val HeadLine = TextStyle(
        fontFamily = FontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        platformStyle = platFormTextStyle,
    )*/
    val Medium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        //platformStyle = platFormTextStyle,
    )
}
