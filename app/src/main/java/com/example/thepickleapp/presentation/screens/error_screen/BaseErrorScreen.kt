package com.example.thepickleapp.presentation.screens.error_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.thepickleapp.R

@Composable
fun BaseErrorScreen(
    errorTitle: String? = null,
    errorMessage: String? = null
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.broken_leaking_flask_lottie_big)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp),
            fontSize = 24.sp,
            text = errorTitle ?: stringResource(id = R.string.base_error_title),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(horizontal = 24.dp)
                .aspectRatio(1f),
            composition = composition,
            progress = { progress }
        )
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp),
            fontSize = 16.sp,
            text = errorMessage ?: stringResource(id = R.string.base_error_message),
            textAlign = TextAlign.Center
        )
    }
}