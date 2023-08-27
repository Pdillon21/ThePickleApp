package com.example.thepickleapp.presentation.common_views.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.ui.theme.pickleAppLightColors

@Composable
fun EndOfListView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 4.dp)
    ) {
        val leftBrush =
            Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    pickleAppLightColors().onSurface
                )
            )
        val rightBrush =
            Brush.horizontalGradient(
                colors = listOf(
                    pickleAppLightColors().onSurface,
                    Color.Transparent
                )
            )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .clip(RoundedCornerShape(100))
                    .background(leftBrush)
            )
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 8.dp),
                text = stringResource(R.string.no_more_results),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .clip(RoundedCornerShape(100))
                    .background(rightBrush)
            )
        }

    }
}


@Preview(showBackground = true, widthDp = 360)
@Composable
fun EndOfListViewPreview() {
    Column() {
        EndOfListView()
    }
}

