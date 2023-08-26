package com.example.thepickleapp.presentation.common_views.general

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun ElevatedContainer(
    modifier: Modifier,
    color: Color = pickleAppColors().femaleAccent,
    sideElevation: Dp = 0.dp,
    bottomElevation: Dp = 0.dp,
    cornerRadius: Dp = 10.dp,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)
    val density = Resources.getSystem().displayMetrics.density
    val cornerRadiusFloat: Float = (cornerRadius * density).value
    val sideElevationFloat = (sideElevation * density).value
    val bottomElevationFloat = (bottomElevation * density).value
    val elevationColor = MaterialTheme.colorScheme.onSurface

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = shape
            )
            .drawBehind {
                if (
                    (sideElevation > 0.dp && bottomElevation >= 0.dp)
                    || (bottomElevation > 0.dp && sideElevation >= 0.dp)
                ) {
                    drawRoundRect(
                        color = elevationColor,
                        topLeft = Offset(
                            x = sideElevationFloat,
                            y = bottomElevationFloat
                        ),
                        size = Size(
                            width = size.width,
                            height = size.height
                        ),
                        cornerRadius = CornerRadius(
                            x = cornerRadiusFloat,
                            y = cornerRadiusFloat
                        )
                    )
                }
            }
            .clip(shape)
            .background(color)
    ) {
        content()
    }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 200)
@Composable
fun ElevetadeContainerPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterVertically
            ),
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            ElevatedContainer(
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
                    .padding(10.dp),
                color = pickleAppColors().extraFiltersSurface,
                bottomElevation = 4.dp,
                sideElevation = 2.dp
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Aló")
                }
            }
            ElevatedContainer(
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
                    .padding(10.dp),
                color = pickleAppColors().extraFiltersSurface,
                bottomElevation = 8.dp,
                sideElevation = 16.dp
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Aló")
                }
            }
        }
        ElevatedContainer(
            modifier = Modifier
                .wrapContentSize(),
            color = pickleAppColors().typeFilterSurface,
            sideElevation = 2.dp,
            bottomElevation = 2.dp
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Aló")
            }
        }
    }
}