package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.presentation.main_screen.search.state.QueryState
import com.example.thepickleapp.presentation.main_screen.search.utils.SearchUtils
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun PickleSearchSummary(queryState: QueryState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = SearchUtils.getSearchSummaryByState(queryState),
            textAlign = TextAlign.End,
            color = pickleAppColors().onSurface
        )
    }
}