package com.seunghoon.designsystem.ui.theme

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.seunghoon.core.designsystem.R

// 디자인에 맞는 폰트 추가해서 사용
private val FontFamily =
    FontFamily(
        listOf(
            Font(
                resId = R.font.pretendard_black,
                weight = FontWeight.Black,
            ),
            Font(
                resId = R.font.pretendard_thin,
                weight = FontWeight.Thin,
            ),
            Font(
                resId = R.font.pretendard_light,
                weight = FontWeight.Light,
            ),
            Font(
                resId = R.font.pretendard_extra_light,
                weight = FontWeight.ExtraLight,
            ),
            Font(
                resId = R.font.pretendard_extra_bold,
                weight = FontWeight.ExtraBold,
            ),
            Font(
                resId = R.font.pretendard_medium,
                weight = FontWeight.Medium,
            ),
            Font(
                resId = R.font.pretendard_regular,
                weight = FontWeight.Normal,
            ),
        )
    )

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
        fontFamily = com.seunghoon.designsystem.ui.theme.FontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Black,
        lineHeight = 24.sp,
        //platformStyle = platFormTextStyle,
    )
}
