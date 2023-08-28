package com.example.thepickleapp.presentation.common_views.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepickleapp.R
import com.example.thepickleapp.data.dao.PagingError
import com.example.thepickleapp.presentation.ui.theme.pickleAppLightColors

@Composable
fun PagingErrorView(
    item: PagingError? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 6.dp)
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
                    .height(8.dp)
                    .clip(RoundedCornerShape(100))
                    .background(leftBrush)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(horizontal = 8.dp),
                    text = item?.errorTitle ?: stringResource(id = R.string.base_error_title),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(horizontal = 8.dp),
                    text = item?.errorMessage ?: stringResource(id = R.string.base_error_message),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .clip(RoundedCornerShape(100))
                    .background(rightBrush)
            )
        }

    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun PagingErrorViewPreview() {
    PagingErrorView()
}