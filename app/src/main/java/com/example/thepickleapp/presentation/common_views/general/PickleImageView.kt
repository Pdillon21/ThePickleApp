package com.example.thepickleapp.presentation.common_views.general

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.thepickleapp.presentation.common_views.BaseErrorScreen
import com.example.thepickleapp.presentation.common_views.EmptyLoadingScreen

@Composable
fun PickleImageView(
    modifier: Modifier,
    url: String?,
    cornerRadius: Dp = 10.dp
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .allowHardware(false)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build(),
        contentScale = ContentScale.FillBounds
    )
    val shape = RoundedCornerShape(cornerRadius)
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = shape
            )
            .clip(shape)
    ) {

        when (painter.state) {
            is AsyncImagePainter.State.Success -> {
                ImageSuccessContainer((painter.state as AsyncImagePainter.State.Success).result.drawable)
            }

            is AsyncImagePainter.State.Error -> {
                BaseErrorScreen(modifier = Modifier.fillMaxSize())
            }

            is AsyncImagePainter.State.Loading -> {
                EmptyLoadingScreen(modifier = Modifier.fillMaxSize())
            }

            is AsyncImagePainter.State.Empty -> {
                BaseErrorScreen()
            }
        }
    }
}

@Composable
fun ImageSuccessContainer(result: Drawable) {
    Image(
        modifier = Modifier.fillMaxSize(),
        bitmap = result.toBitmap().asImageBitmap(),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}
