package com.example.thepickleapp.presentation.common_views.list_cells

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.presentation.ui.theme.pickleAppLightColors

@Composable
fun PagingCell() {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffset by transition.animateFloat(
        initialValue = -1 * size.width.toFloat(),
        targetValue = size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 600
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        pickleAppLightColors().charactersAccent,
                        pickleAppLightColors().locationsAccent,
                        pickleAppLightColors().locationsAccent,
                        pickleAppLightColors().episodesAccent
                    ),
                    start = Offset(startOffset, 0f),
                    end = Offset(startOffset + size.width, size.height.toFloat())
                )
            )
            .onPlaced {
                size = it.size
            }
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 100)
@Composable
fun PagingCellPreview() {
    PagingCell()
}