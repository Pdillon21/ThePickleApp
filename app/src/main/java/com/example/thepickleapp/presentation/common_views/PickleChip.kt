package com.example.thepickleapp.presentation.common_views

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickleChip(
    iconResource: Int,
    text: String,
    isSelected: Boolean,
    contentDescription: String,
    onClick: () -> Unit
) {
    InputChip(
        selected = isSelected,
        label = { Text(text = text) },
        onClick = { onClick() },
        avatar = {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = contentDescription
            )
        }
    )
}