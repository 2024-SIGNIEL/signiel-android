package com.seunghoon.generator.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.DefaultAlpha
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.generator.R
import com.seunghoon.generator.SignielDatabase
import com.seunghoon.generator.dao.PayDao

@Composable
fun PayScreen(navController: NavController) {
    val context = LocalContext.current
    val database = Room.databaseBuilder(context, SignielDatabase::class.java, "pay-database")
        .fallbackToDestructiveMigration().build()
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
            text = "지출",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        PayChart()
        Spacer(modifier = Modifier.height(36.dp))
        History(database.getPayDao())
    }
}

@Composable
private fun History(payDao: PayDao) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.test),
            contentDescription = null,
        )
    }
}

@Composable
private fun PayChart() {
    ProvideChartStyle {
        val marker = rememberMarker()
        val datasetLineSpec = remember {
            arrayListOf(
                LineChart.LineSpec(
                    lineColor = Colors.Main.toArgb(),
                    lineBackgroundShader = DynamicShaders.fromBrush(
                        brush = Brush.verticalGradient(
                            listOf(
                                Colors.Main.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                                Colors.Main.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_END)
                            )
                        )
                    ),
                )
            )
        }
        val list = mutableListOf<FloatEntry>()
        repeat(5) {
            list.add(FloatEntry(it.toFloat(), it.toFloat()))
        }
        Box {
            Chart(
                //modifier = Modifier.padding(horizontal = 16.dp),
                chart = lineChart(lines = datasetLineSpec),
                model = entryModelOf(list),
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(),
                chartScrollState = rememberChartScrollState(),
                marker = marker,
            )
        }
    }
}
