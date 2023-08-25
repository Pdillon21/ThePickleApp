package com.example.thepickleapp.presentation.common_views.general

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DataPill(
    modifier: Modifier,
    color: Color = Color.Blue,
    textColor : Color = Color.Black,
    cornerRadius: Dp = 10.dp,
    text: String
) {
    val shape = RoundedCornerShape(cornerRadius)

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = shape
            )
            .clip(shape)
            .background(color)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 2.dp),
            text = text,
            fontSize = 12.sp,
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}