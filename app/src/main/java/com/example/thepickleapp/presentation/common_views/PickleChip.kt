package com.example.thepickleapp.presentation.common_views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickleChip(
    iconResource: Int?,
    text: String,
    isSelected: Boolean,
    contentDescription: String,
    onClick: () -> Unit
) {
    InputChip(
        modifier = Modifier
            .padding(0.dp),
        selected = isSelected,
        label = { Text(text = text) },
        onClick = { onClick() },
        avatar = {
            iconResource?.let {
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = contentDescription
                )
            }
        }
    )
}