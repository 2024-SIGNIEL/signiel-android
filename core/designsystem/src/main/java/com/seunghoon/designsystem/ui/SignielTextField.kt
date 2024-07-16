package com.seunghoon.designsystem.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seunghoon.designsystem.ui.theme.Colors
import com.seunghoon.designsystem.ui.theme.Typography

@Composable
fun SignielTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    showTitle: Boolean = false,
    isError: Boolean = false,
    enable: Boolean = true,
    onValueChange: (String) -> Unit,
    isHaveBottomLine: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    var focused by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (!showTitle && value.isEmpty()) 1f
        else 0f,
        label = "",
    )
    val color by animateColorAsState(
        targetValue = if (!focused) Colors.Gray
        else if (isError) Colors.Error
        else Colors.Main,
        label = "",
    )
    val x by animateDpAsState(
        targetValue = if (focused && showTitle) (-12).dp
        else 0.dp,
        label = "",
    )
    val y by animateDpAsState(
        targetValue = if (focused && showTitle) (-52).dp
        else 0.dp,
        label = "",
    )
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 12.dp,
                )
                .padding(start = 4.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        focused = it.isFocused
                    },
                maxLines = 3,
                value = value,
                onValueChange = onValueChange,
                // textStyle = SavageTypography.Body3,
                singleLine = true,
                enabled = enable,
                decorationBox = { it() },
                keyboardActions = keyboardActions
            )
            Text(
                modifier = Modifier
                    .alpha(alpha)
                    .offset(
                        y = y,
                        x = x,
                    ),
                text = hint,
                color = Colors.Gray,
                style = Typography.Medium,
            )
        }
        if (isHaveBottomLine) Divider(
            modifier = Modifier.fillMaxWidth(),
            color = color,
            thickness = 2.dp,
        )
    }
}

@Composable
internal fun SavageBoxTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
) {
    var focused by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (value.isEmpty()) 1f
        else 0f,
        label = "",
    )
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            //.background(SavageColor.Gray10)
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp,
            )
            .onFocusChanged {
                focused = it.hasFocus
            },
        value = value,
        onValueChange = onValueChange,
    ) { innerTextField ->
        innerTextField()
        Text(
            modifier = Modifier.alpha(alpha),
            text = hint,
            /*style = SavageTypography.Body2,
            color = SavageColor.Gray40,*/
        )

    }
}

@Preview
@Composable
fun SavageTextFieldPreview() {
    SignielTextField(
        value = "",
        hint = "힌트",
        onValueChange = {

        },
        isHaveBottomLine = false,
    )
}
