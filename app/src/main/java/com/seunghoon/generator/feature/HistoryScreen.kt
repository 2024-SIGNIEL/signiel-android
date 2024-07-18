package com.seunghoon.generator.feature

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.generator.SignielDatabase
import com.seunghoon.generator.entity.Pay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat

@Composable
fun HistoryScreen() {
    val context = LocalContext.current
    val room = Room.databaseBuilder(context, SignielDatabase::class.java, "pay-database")
        .fallbackToDestructiveMigration().build()
    val dao = room.getPayDao()
    val history = remember { mutableStateListOf<Pay>() }
    LaunchedEffect(Unit) {
        launch(Dispatchers.IO) {
            runCatching {
                dao.queryPay()
            }.onSuccess {
                history.addAll(it)
                history.reverse()
            }.onFailure {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "내역을 조회하던 도중 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Text(
            modifier = Modifier
                .padding(
                    vertical = 20.dp,
                    horizontal = 24.dp,
                ),
            text = "지출 내역",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(history) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Text(
                            text = item.use,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = "${item.year}-${item.month}-${item.day}",
                            fontSize = 14.sp,
                            color = Colors.Gray,
                        )
                    }
                    Text(
                        text = "${DecimalFormat("#,###").format(item.amount)}원",
                        color = Colors.Main,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
}
