package com.seunghoon.generator.feature.home

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.designsystem.ui.theme.Typography
import com.seunghoon.generator.R
import java.text.DecimalFormat

val categoryImages = listOf(
    R.drawable.ic_culture,
    R.drawable.ic_life,
    R.drawable.ic_hobby,
)

val categories = listOf(
    "문화", "생활", "취미"
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    var started by remember { mutableStateOf(false) }
    val todayPaid by animateIntAsState(
        targetValue = if (started) 50000
        else 0,
        label = "",
        animationSpec = tween(durationMillis = 2000)
    )
    LaunchedEffect(Unit) {
        started = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        Header(title = "홈")
        Column(modifier = Modifier.background(Colors.Background_Gray)) {
            Spacer(modifier = Modifier.height(16.dp))
            PayCard(todayPaid = todayPaid)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "카테고리",
                style = Typography.Medium.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Categories()
        Calendar
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
                        color = Colors.Main,
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
                /*Image(
                    modifier = Modifier
                        .align(Alignment.End)
                        .size(140.dp),
                    painter = painterResource(id = categoryImages[it]),
                    contentDescription = null,
                    //contentScale = ContentScale.Crop,
                )*/
            }
        }
    }
}

@Composable
private fun PayCard(
    todayPaid: Int,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp)
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "지출 내역",
                        style = Typography.Medium.copy(
                            fontWeight = FontWeight.Light,
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
            Text(
                text = "${DecimalFormat("#,###").format(todayPaid)}원",
                style = Typography.Medium.copy(
                    fontSize = 48.sp,
                    color = Colors.White,
                    fontWeight = FontWeight.Normal,
                ),
            )
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
                    vertical = 22.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = R.drawable.ic_fino),
                contentDescription = null,
            )
        }
        //HorizontalDivider(thickness = 1.dp)
    }
}
