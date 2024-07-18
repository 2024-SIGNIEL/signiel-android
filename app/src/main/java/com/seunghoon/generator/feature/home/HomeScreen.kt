package com.seunghoon.generator.feature.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.designsystem.ui.theme.Typography
import com.seunghoon.generator.Keys
import com.seunghoon.generator.R
import com.seunghoon.generator.SharedPreferenceManager
import com.seunghoon.generator.SignielDatabase
import com.seunghoon.generator.dao.PayDao
import com.seunghoon.generator.entity.PayType
import com.seunghoon.generator.navigation.NavigationRoute
import com.seunghoon.generator.ui.SignielCalendar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.time.LocalDateTime

val categoryImages = listOf(
    R.drawable.ic_culture,
    R.drawable.ic_life,
    R.drawable.ic_hobby,
)

val calendarPayHistory = Array(31) { 0 }

val categories = listOf(
    "문화", "생활", "취미"
)

@SuppressLint("MissingInflatedId")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavController,
    navController: NavController,
) {
    var started by remember { mutableStateOf(false) }
    var current by remember { mutableIntStateOf(0) }
    var last by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val database =
        Room.databaseBuilder(context, SignielDatabase::class.java, "pay-database")
            .fallbackToDestructiveMigration().build()
    val todayPaid by animateIntAsState(
        targetValue = if (started) current
        else last,
        label = "",
        animationSpec = tween(durationMillis = 2000)
    )
    val maxPay = SharedPreferenceManager.sharedPreference.getString(Keys.MAX_PAY, "0")?.toInt() ?: 0
    val pinMoney =
        SharedPreferenceManager.sharedPreference.getString(Keys.PIN_MONEY, "0")?.toInt() ?: 0
    val maxDay = daysInMonth(
        month = LocalDateTime.now().monthValue,
        year = LocalDateTime.now().year,
    )

    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = remember { SwipeRefreshState(isRefreshing) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.money_raain))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isRefreshing,
        restartOnPlay = false
    )

    LaunchedEffect(Unit) {
        started = true
        launch(Dispatchers.IO) {
            LocalDateTime.now().apply {
                var sum = 0
                database.getPayDao().queryTodayPay(
                    year = year,
                    month = monthValue,
                    day = dayOfMonth,
                ).forEach {
                    if (it.payType == PayType.WITHDRAWAL) {
                        sum += it.amount
                    } else {
                        sum -= it.amount
                    }
                }
                last = current
                current = sum
            }
        }
        getCalendarPayHistory(
            payDao = database.getPayDao(),
            maxDay = daysInMonth(
                month = LocalDateTime.now().monthValue,
                year = LocalDateTime.now().year,
            ),
            month = LocalDateTime.now().monthValue,
            year = LocalDateTime.now().year,
        )
    }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(2000)
            isRefreshing = false
        }
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            Log.d("TEST","1")
            isRefreshing = true
        },
        indicator = { state, trigger ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                if (state.isRefreshing) {
                    LottieAnimation(
                        composition = composition,
                        progress = progress,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
        ) {
            Header(title = "홈")
            Column(
                modifier = Modifier
                    .background(Colors.Background_Gray)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
            ) {
                SignielCalendar(
                    calendarPayHistory = calendarPayHistory,
                    maxPay = maxPay,
                    onChange = { month, year ->
                        getCalendarPayHistory(
                            payDao = database.getPayDao(),
                            maxDay = daysInMonth(
                                month = month,
                                year = year,
                            ),
                            month = month,
                            year = year,
                        )
                    },
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(Colors.Main)
                        )
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "한도 준수",
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(Colors.OnError),
                        )
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "한도 초과",
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        thickness = 1.dp,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 32.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    color = Colors.Black,
                                )
                            ) {
                                append("7월에는 총 ")
                            }
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 24.sp,
                                    color = Colors.Main,
                                )
                            ) {
                                append("10,000원")
                            }
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    color = Colors.Black,
                                )
                            ) {
                                append(" 절약했어요!")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(contentAlignment = Alignment.CenterStart) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp)
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Colors.Gray10),
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .height(8.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Colors.Main)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "0원",
                            style = TextStyle(
                                color = Colors.Gray,
                            )
                        )
                        Text(
                            text = "${if (pinMoney == 0) 0 else DecimalFormat("#,###").format(maxPay * maxDay)}원",
                            style = TextStyle(
                                color = Colors.Gray,
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                PayCard(
                    todayPaid = todayPaid,
                    max = maxPay,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            PayCard(
                todayPaid = todayPaid,
                max = maxPay,
                navController = navController,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun Categories() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(3) {
            Column(
                modifier = Modifier
                    .width(140.dp)
                    .height(100.dp)
                    .background(
                        color = Colors.Tertiary,
                        shape = RoundedCornerShape(12.dp),
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = categories[it],
                    style = Typography.Medium.copy(
                        color = Colors.White,
                        fontSize = 18.sp,
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun PayCard(
    todayPaid: Int,
    max: Int,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(horizontal = 1.dp)
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF7B9DE8),
                        Color(0xFFD37CD4),
                    )
                ),
                shape = RoundedCornerShape(10.dp),
            )
            .padding(
                horizontal = 12.dp,
                vertical = 24.dp,
            ),
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "오늘의 총 지출금액",
                    style = Typography.Medium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = Colors.White,
                )
                Icon(
                    modifier = Modifier
                        .size(18.dp)
                        .padding(start = 2.dp),
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = null,
                    tint = Colors.White,
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        navController.navigate(NavigationRoute.Main.HISTORY)
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "지출 내역",
                        style = Typography.Medium.copy(
                            fontWeight = FontWeight.Normal,
                        ),
                        color = Colors.White
                    )
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Colors.White,
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = "한도 ${DecimalFormat("#,###").format(max)}원",
                    style = Typography.Medium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Colors.White,
                    )
                )
                Text(
                    text = "${DecimalFormat("#,###").format(todayPaid)}원",
                    style = Typography.Medium.copy(
                        fontSize = 48.sp,
                        color = Colors.White,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
            }
        }
    }
}

@Composable
fun Header(title: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 12.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.ic_fino_text),
                contentDescription = null,
            )
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
            )
        }
    }
}

fun getCalendarPayHistory(
    payDao: PayDao,
    maxDay: Int,
    year: Int,
    month: Int,
) {
    val array = Array(31) { 0 }
    CoroutineScope(Dispatchers.IO).launch {
        runCatching {
            payDao.queryByMonthValue(
                year = year,
                month = month,
            )
        }.onSuccess {
            it.forEachIndexed { index, element ->
                when (element.payType) {
                    PayType.DEPOSIT -> {
                        array[element.day.toInt()] += element.amount
                    }

                    else -> array[element.day.toInt()] -= element.amount
                }
                calendarPayHistory[index] = array[index]
            }
            (1..31).forEach { i ->
                calendarPayHistory[i - 1] = array[i - 1]
            }

        }
    }
}

fun daysInMonth(month: Int, year: Int): Int {
    return when (month) {
        2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
        4, 6, 9, 11 -> 30
        else -> 31
    }
}
