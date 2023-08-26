package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun PickleSearchBar(modifier: Modifier, onSearchClicked: (String) -> Unit) {
    var currentQuery by remember {
        mutableStateOf("")
    }
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            SearchIconButton() {
                onSearchClicked(currentQuery)
            }
            SearchEditText(currentQuery) { newQuerry ->
                currentQuery = newQuerry
            }
        }
    }
}

@Composable
fun SearchIconButton(onIconClick: () -> Unit) {
    IconButton(onClick = {
        onIconClick()
    }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "Search Icon",
            tint = pickleAppColors().onSurface
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchEditText(currentQuery: String, onQuerryChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = currentQuery,
        onValueChange = { newQuerry ->
            onQuerryChange(newQuerry)
        }
    )
}
