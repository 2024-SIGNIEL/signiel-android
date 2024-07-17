package com.seunghoon.generator.feature.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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

@Composable
fun PayScreen(navController: NavController) {
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
        History()
    }
}

@Composable
private fun History() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Colors.Gray,
                shape = RoundedCornerShape(10.dp),
            )
    ) {

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
        repeat(31) {
            list.add(FloatEntry(it.toFloat(), it.toFloat()))
        }
        Box {
            Chart(
                modifier = Modifier.padding(horizontal = 16.dp),
                chart = lineChart(lines = datasetLineSpec),
                model = entryModelOf(list),
                startAxis = rememberStartAxis(/*guideline = null*/),
                bottomAxis = rememberBottomAxis(/*guideline = null*/),
                chartScrollState = rememberChartScrollState(),
                marker = marker,
            )
        }
    }
}
