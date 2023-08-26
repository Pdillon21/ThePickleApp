package com.example.thepickleapp.presentation.common_views.general

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.main_screen.search.utils.CharacterStatus
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun PickleChip(
    modifier: Modifier = Modifier,
    iconResource: Int?,
    text: String,
    isSelected: Boolean,
    cornerRadius: Dp = 10.dp,
    fontSize: TextUnit = 14.sp,
    colorPrimary: Color = pickleAppColors().genderlessAccent,
    colorSecondary: Color = pickleAppColors().onSurface,
    clickable: Boolean = true,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Transparent else colorSecondary,
                shape = shape
            )
            .clip(shape)
            .background(if (isSelected) colorSecondary else colorPrimary)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(),
                enabled = clickable,
                onClickLabel = null,
                role = Role.Button,
                onClick = { onClick() }
            )
    ) {
        Row(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.Start
            )
        ) {
            iconResource?.let {
                Icon(
                    modifier = Modifier
                        .width(14.dp)
                        .aspectRatio(1f),
                    painter = painterResource(id = iconResource),
                    contentDescription = null,
                    tint = if (isSelected) colorPrimary else colorSecondary
                )
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp),
                text = text,
                fontSize = fontSize,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) colorPrimary else colorSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview
@Composable
fun PickleChipPreview() {
    Column() {
        PickleChip(
            iconResource = R.drawable.sentiment_neutral,
            text = CharacterStatus.unknown,
            isSelected = true
        ) {

        }
        PickleChip(
            iconResource = R.drawable.sentiment_neutral,
            text = CharacterStatus.unknown,
            isSelected = false
        ) {

        }
    }
}

