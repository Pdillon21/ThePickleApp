package com.example.thepickleapp.presentation.common_views.general

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.R

@Composable
fun ElevatedButton(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    sideElevation: Dp = 0.dp,
    bottomElevation: Dp = 0.dp,
    color: Color = Color.Blue,
    clickable: Boolean,
    content: @Composable () -> Unit,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)
    val density = Resources.getSystem().displayMetrics.density
    val cornerRadiusFloat: Float = (cornerRadius * density).value
    val sideElevationFloat = (sideElevation * density).value
    val bottomElevationFloat = (bottomElevation * density).value

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = shape
            )
            .drawBehind {
                if (
                    (sideElevation > 0.dp && bottomElevation >= 0.dp)
                    || (bottomElevation > 0.dp && sideElevation >= 0.dp)
                ) {
                    drawRoundRect(
                        color = Color.Black,
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
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(),
                enabled = clickable,
                onClickLabel = null,
                role = Role.Button,
                onClick = { onClick() }
            )
    ) {
        content()
    }
}

@Preview
@Composable
fun ElevatedButtonPreview() {
    Column() {
        ElevatedButton(
            bottomElevation = 2.dp,
            sideElevation = 2.dp,
            clickable = true,
            content = {
                Row(
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 4.dp,
                        alignment = Alignment.Start
                    )
                ) {
                    Text(
                        text = "ShowFilters"
                    )
                    Icon(
                        modifier = Modifier
                            .width(14.dp)
                            .aspectRatio(1f),
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        ) {

        }
        ElevatedButton(
            clickable = true,
            bottomElevation = 2.dp,
            sideElevation = 2.dp,
            content = {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .width(14.dp)
                        .aspectRatio(1f),
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_up_24),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        ) {

        }
    }
}