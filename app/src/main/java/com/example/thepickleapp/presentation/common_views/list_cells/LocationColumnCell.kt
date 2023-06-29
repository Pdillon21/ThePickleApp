package com.example.thepickleapp.presentation.common_views.list_cells

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepickleapp.data.dao.PickleResultDaoBase.LocationDao
import com.example.thepickleapp.domain.utils.getFormatedCreationDate
import com.example.thepickleapp.presentation.common_views.BasePickleCard
import com.example.thepickleapp.presentation.common_views.PickleChip

@Composable
fun LocationColumnCell(item: LocationDao) {
    BasePickleCard {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = item.name ?: "NoName",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(start = 4.dp)
            ) {
                PickleChip(
                    iconResource = null,
                    text = item.type ?: "Unknown",
                    isSelected = false,
                    contentDescription = "Location Type"
                ) {}
                Spacer(modifier = Modifier.width(4.dp))
                PickleChip(
                    iconResource = null,
                    text = item.dimension ?: "Unknown",
                    isSelected = false,
                    contentDescription = "Location Dimension"
                ) {}
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Residents: ${item.residents?.size ?: "Unknown number"}",
                    modifier = Modifier.padding(
                        start = 4.dp
                    )
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = "Created: ${item.getFormatedCreationDate()}"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLocationColumnCell() {
    LocationColumnCell(
        item = LocationDao(
            id = 1,
            name = "Earth",
            type = "Planet",
            dimension = "Dimension C-137",
            residents = listOf("one", "two"),
            url = "",
            created = "2017-11-10T12:42:04.162Z"
        )
    )
}