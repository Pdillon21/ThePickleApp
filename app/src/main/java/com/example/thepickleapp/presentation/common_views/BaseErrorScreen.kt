package com.example.thepickleapp.presentation.common_views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.thepickleapp.presentation.utils.ErrorData
import com.example.thepickleapp.presentation.utils.ErrorUtils

@Composable
fun BaseErrorScreen(
    modifier: Modifier = Modifier,
    errorData: ErrorData? = ErrorUtils.getDefaultErrorData()
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //ToDo pasar strings a values
            Text(
                text = errorData?.errorTitle
                    ?: "Ups! Something went wrong!"
            )
            Text(
                text = errorData?.errorMessage
                    ?: "We couldn't perform this action for this universe."
            )
        }
    }
}