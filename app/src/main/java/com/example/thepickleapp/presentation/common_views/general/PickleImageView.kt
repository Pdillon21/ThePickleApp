package com.example.thepickleapp.presentation.common_views.general

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.common_views.EmptyLoadingScreen
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

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
            .size(Size.ORIGINAL)
            .build(),
        contentScale = ContentScale.FillBounds
    )
    val shape = RoundedCornerShape(cornerRadius)
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = pickleAppColors().onSurface,
                shape = shape
            )
            .clip(shape)
    ) {

        when (painter.state) {
            is AsyncImagePainter.State.Success -> {
                ImageSuccessContainer((painter.state as AsyncImagePainter.State.Success).result.drawable)
            }

            is AsyncImagePainter.State.Loading -> {
                EmptyLoadingScreen(modifier = Modifier.fillMaxSize())
            }

            else -> {
                EmptyImagePlaceHolder()
            }
        }
    }
}

@Composable
fun EmptyImagePlaceHolder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_image_24),
            contentDescription = null,
            tint = pickleAppColors().onSearchBarHint
        )
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

@Preview(showBackground = true, widthDp = 80, heightDp = 80)
@Composable
fun EmptyImagePlaceHolderPreview() {
    EmptyImagePlaceHolder()
}

