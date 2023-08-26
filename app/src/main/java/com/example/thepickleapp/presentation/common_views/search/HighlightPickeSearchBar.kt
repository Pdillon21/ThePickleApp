package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun HighlightPickleSearchBar(
    showDynamicLegend: Boolean = true,
    enableHighlight: Boolean = true,
    legend: String?,
    query: String?,
    onQueryChange: (String) -> Unit
) {
    legend?.let {
        Text(
            text = legend + if (showDynamicLegend) {
                query ?: ""
            } else {
                ""
            }
        )
    }
    val highlight = !(query.isNullOrEmpty() || !enableHighlight)
    HighlightSearchBarCard(highlight, query) { newQuery ->
        onQueryChange(newQuery)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighlightSearchBarCard(highlight: Boolean, query: String?, onQueryChange: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .defaultMinSize(
                        minHeight = 1.dp,
                        minWidth = 10.dp
                    ),
                value = query ?: "",
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = pickleAppColors().searchBarSurface
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 14.sp
                ),
                onValueChange = { newQuerry ->
                    onQueryChange(newQuerry)
                })
        }
    }
}
