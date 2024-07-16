package com.seunghoon.designsystem.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.designsystem.ui.theme.Typography

@Composable
fun SignielButton(
    modifier: Modifier = Modifier,
    text: String = "default",
    onClick: () -> Unit,
    isKeyShow: Boolean = false,
    isAbleClick: Boolean = true,
) {
    var isPressed by remember { mutableStateOf(false) }

    val colors = ButtonDefaults.buttonColors(
        containerColor = when {
            isAbleClick -> if (isPressed) Colors.Main else Colors.Main
            else -> if (isPressed) Colors.Main_Disabled else Colors.Main_Disabled
        },
        contentColor = Color.White,
        disabledContainerColor = Colors.Main_Disabled,
        disabledContentColor = Colors.Main_Disabled,
    )

    val padding by animateDpAsState(
        targetValue = if (isKeyShow) 0.dp
        else 0.dp,
        label = "",
    )

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f
        else 1f,
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isPressedState by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressedState) {
        isPressed = isPressedState
    }

    Button(
        onClick = {
            onClick()
        },
        enabled = isAbleClick,
        interactionSource = interactionSource,
        shape = if (!isKeyShow) RoundedCornerShape(10.dp) else RoundedCornerShape(0.dp),
        colors = colors,
        contentPadding = PaddingValues(vertical = 14.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .padding(horizontal = padding),
    ) {
        Text(
            text = text,
            style = Typography.Medium.copy(fontWeight = FontWeight.Bold),
            color = Colors.White,
        )
    }
}
