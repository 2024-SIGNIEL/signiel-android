package com.seunghoon.generator.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SignielCalendar(
    modifier: Modifier = Modifier,
    fetchDate: (String) -> Unit,
) {
    var currentYear by remember { mutableIntStateOf(LocalDate.now().year) }
    var currentMonth by remember { mutableIntStateOf(LocalDate.now().monthValue) }
    val weekList = listOf("월", "화", "수", "목", "금", "토", "일")
    var update by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        update = true
    }
    LaunchedEffect(currentMonth) {
        update = true
    }

    fun daysInMonth(month: Int, year: Int): Int {
        return when (month) {
            2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
            4, 6, 9, 11 -> 30
            else -> 31
        }
    }

    val firstDayOfMonth = LocalDate.of(
        currentYear,
        currentMonth,
        1,
    )
    val dayOfWeek = firstDayOfMonth.dayOfWeek.value
    val daysInCurrentMonth = daysInMonth(currentMonth, currentYear)
    val dates: List<CalendarDate> = (1..daysInCurrentMonth).map { day ->
        CalendarDate(day, currentMonth, currentYear)
    }

    Column(modifier = modifier.fillMaxHeight(0.7f)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "keyboardArrowLeft",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        update = false
                        if (currentMonth - 1 < 1) {
                            currentYear--
                            currentMonth = 12
                        } else currentMonth--
                    }
            )
            Text(
                text = "${currentYear}년 ${currentMonth}월",
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "keyboardArrowRight",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        update = false
                        if (currentMonth + 1 > 12) {
                            currentYear++
                            currentMonth = 1
                        } else currentMonth++
                    }
            )
        }
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp,
                )
                .clip(RoundedCornerShape(24.dp))
        ) {
            FlowRow(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 12.dp
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                weekList.forEach { day ->
                    Text(
                        text = day,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
            if (update) {
                FlowRow(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 12.dp,
                        )
                        .fillMaxWidth(),
                    maxItemsInEachRow = 7,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    var startOfMonth = 1
                    var select by remember { mutableStateOf("") }
                    dates.forEach { date ->
                        val today =
                            if (date.day < 10) "${date.year}-${date.month}-0${date.day}"
                            else "${date.year}-${date.month}-${date.day}"
                        if (dayOfWeek > startOfMonth) {
                            for (i in 2..dayOfWeek) {
                                Spacer(modifier = Modifier.weight(1f))
                                startOfMonth++
                            }
                        }
                        startOfMonth++
                        Column(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .weight(1f)

                                .clip(CircleShape)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                ) {
                                    select = "${date.year}${date.month}${date.day}"
                                    fetchDate(select)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            // 일
                            Text(
                                modifier = Modifier
                                    /*.background(
                                        color = Color.Gray,
                                        shape = CircleShape,
                                    )*/
                                    .clip(CircleShape)
                                    .padding(4.dp),
                                text = (date.day).toString(),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                        if (startOfMonth == daysInCurrentMonth + dayOfWeek) {
                            for (i in 1..7) Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignielCalendarPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        SignielCalendar {

        }
    }
}

data class CalendarDate(
    val day: Int,
    val month: Int,
    val year: Int
)
