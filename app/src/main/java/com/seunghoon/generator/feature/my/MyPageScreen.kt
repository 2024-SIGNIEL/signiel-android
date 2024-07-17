package com.seunghoon.generator.feature.my

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seunghoon.core.designsystem.R
import com.seunghoon.designsystem.ui.SignielButton
import com.seunghoon.designsystem.ui.SignielTextField
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.generator.Keys
import com.seunghoon.generator.SharedPreferenceManager
import com.seunghoon.generator.navigation.NavigationRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val (showPinSheet, onChangeShowPinSheet) = remember {
        mutableStateOf(false)
    }
    val (showLimitSheet, onChangeShowLimitSheet) = remember {
        mutableStateOf(false)
    }

    var pin by remember { mutableStateOf("") }
    var money by remember { mutableStateOf("") }

    if (showPinSheet) {
        ModalBottomSheet(
            onDismissRequest = { onChangeShowPinSheet(false) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp)
                    .padding(horizontal = 24.dp),
            ) {
                Text(
                    text = "받은 용돈을 입력해주세요",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(48.dp))
                SignielTextField(
                    value = pin,
                    hint = "0원",
                    onValueChange = { pin = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(40.dp))
                SignielButton(
                    onClick = {
                        onChangeShowPinSheet(false)
                        SharedPreferenceManager.sharedPreference
                            .edit()
                            .putString(Keys.PIN_MONEY, pin)
                            .commit()
                    },
                    text = "용돈 저장하기",
                    isAbleClick = pin.isNotBlank()
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    if (showLimitSheet) {
        ModalBottomSheet(
            onDismissRequest = { onChangeShowLimitSheet(false) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp)
                    .padding(horizontal = 24.dp),
            ) {
                Text(
                    text = "하루에 쓸 금액의 한도를 설정하세요",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(48.dp))
                SignielTextField(
                    value = money,
                    hint = "0원",
                    onValueChange = { money = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "위의 금액 이상 사용시 달력 아이콘이 붉게 변합니다",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                )
                Spacer(modifier = Modifier.height(40.dp))
                SignielButton(
                    onClick = {
                        onChangeShowLimitSheet(false)
                        SharedPreferenceManager.sharedPreference
                            .edit()
                            .putString(Keys.MAX_PAY, money)
                            .commit()
                    },
                    text = "한도 설정하기",
                    isAbleClick = money.isNotBlank()
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        Text(
            modifier = Modifier
                .padding(
                    vertical = 20.dp,
                    horizontal = 24.dp,
                ),
            text = "마이페이지",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = 24.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(30.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "홍길동",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "님, 안녕하세요",
                        fontSize = 16.sp,
                    )
                }
                Text(
                    text = "abddsas@gmail, wlqdprkryhtlvek",
                    fontSize = 12.sp,
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "내 신용 점수",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Colors.Main_Disabled)
                    .padding(vertical = 14.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = "2등급",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
                Text(
                    text = "917점",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "카테고리",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
            ) {
                Category(
                    icon = com.seunghoon.generator.R.drawable.ic_shop,
                    title = "쇼핑",
                    percent = "42%",
                )
                Category(
                    icon = com.seunghoon.generator.R.drawable.ic_food,
                    title = "음식",
                    percent = "36%",
                )
                Category(
                    icon = com.seunghoon.generator.R.drawable.ic_cloth,
                    title = "의류",
                    percent = "13%",
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "설정",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onChangeShowPinSheet(true) }
                    .padding(
                        vertical = 6.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = com.seunghoon.generator.R.drawable.ic_request_money),
                    contentDescription = null,
                    tint = Colors.Main_Disabled,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "용돈 추가",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onChangeShowLimitSheet(true) }
                    .padding(
                        vertical = 6.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = com.seunghoon.generator.R.drawable.ic_calendar),
                    contentDescription = null,
                    tint = Colors.Main_Disabled,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "일일 한도 설정",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(NavigationRoute.Auth.ON_BOARDING) }
                    .padding(
                        vertical = 6.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = com.seunghoon.generator.R.drawable.ic_logout),
                    contentDescription = null,
                    tint = Colors.Error,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "로그아웃",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
private fun Category(
    @DrawableRes icon: Int,
    title: String,
    percent: String,
) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Colors.Tertiary)
            .padding(10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = percent,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.End
        )
    }
    Spacer(modifier = Modifier.width(8.dp))
}