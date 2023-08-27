package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.common_views.general.ElevatedContainer
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun PickleElevatedSearchBar(
    modifier: Modifier = Modifier,
    hint: String? = null,
    buttonVisible: Boolean = false,
    baseColor: Color = pickleAppColors().searchBarSurface,
    iconColor: Color = pickleAppColors().onSurface,
    currentQuery: String? = "",
    onQuerryChange: (String) -> Unit = {},
    onButtonClicked: (String) -> Unit = {}
) {
    ElevatedContainer(
        modifier =
        modifier
            .fillMaxWidth(),
        sideElevation = 2.dp,
        bottomElevation = 2.dp,
        color = pickleAppColors().searchBarSurface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            PickleSearchBarTextField(
                modifier = Modifier.weight(1f),
                currentQuery = currentQuery,
                hint = hint,
                baseColor = baseColor
            ){ newQuery ->
                onQuerryChange(newQuery)
            }
            if (buttonVisible) {
                PickleSearchBarButton(iconColor) {
                    onButtonClicked(currentQuery ?: "")
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickleSearchBarTextField(
    modifier: Modifier,
    currentQuery: String?,
    hint : String?,
    baseColor: Color,
    onQuerryChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = currentQuery ?: "",
        placeholder = {
            hint?.let {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        color = pickleAppColors().onSearchBarHint
                    )
                }
            }
        },
        onValueChange = { newQuerry ->
            onQuerryChange(newQuerry)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = baseColor,
            cursorColor = pickleAppColors().episodesAccent,
            selectionColors = TextSelectionColors(
                handleColor = pickleAppColors().episodesAccent,
                backgroundColor = pickleAppColors().genderlessAccent
            ),
            focusedIndicatorColor = pickleAppColors().episodesAccent
        )
    )
}

@Composable
fun PickleSearchBarButton(iconColor: Color, onButtonClicked: () -> Unit) {
    IconButton(
        modifier = Modifier
            .wrapContentSize()
            .width(24.dp)
            .aspectRatio(1f),
        onClick = {
            onButtonClicked()
        }) {
        Icon(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = null,
            tint = iconColor
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun SearchViewPreviews() {
    Column() {
        PickleElevatedSearchBar()
        PickleElevatedSearchBar(buttonVisible = true)
    }
}
