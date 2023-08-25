package com.example.thepickleapp.presentation.common_views.list_cells


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepickleapp.data.dao.PickleResultDaoBase.LocationDao
import com.example.thepickleapp.presentation.common_views.general.DataPill
import com.example.thepickleapp.presentation.common_views.general.ElevatedContainer
import com.example.thepickleapp.presentation.common_views.utils.TextUtils

@Composable
fun LocationColumnCell(item: LocationDao) {
    ElevatedContainer(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = Color(0xffC9FCFF),
        bottomElevation = 2.dp,
        sideElevation = 2.dp
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-36).dp, x = (-10).dp),
            text = TextUtils.getInitialsAndNumbers(item.name ?: "Unknown"),
            color = Color.Black.copy(alpha = 0.1f),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            fontSize = 96.sp,
            overflow = TextOverflow.Clip,
            maxLines = 1
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            LocationTypePill(item.type)
            Spacer(modifier = Modifier.height(6.dp))
            LocationName(item.name)
            Spacer(modifier = Modifier.height(8.dp))
            DimensionData(item.dimension)
            Spacer(modifier = Modifier.height(8.dp))
            ResidentsData(item.residents)
        }
    }
}

@Composable
fun ResidentsData(residents: List<String>?) {
    val residenstsString = if (residents.isNullOrEmpty()) {
        "Unknown"
    } else {
        residents.size.toString()
    }
    Row {
        Text(
            text = "Dimension:",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = residenstsString,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
fun DimensionData(dimension: String?) {
    Row {
        Text(
            text = "Dimension:",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = dimension ?: "Unknown",
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
fun LocationName(name: String?) {
    Text(
        text = name ?: "Unknown",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        maxLines = 3
    )
}

@Composable
fun LocationTypePill(type: String?) {
    DataPill(
        modifier = Modifier.wrapContentSize(),
        text = type ?: "Unknown",
        color = Color(0xffF7EEFF),
        textColor = Color.Black
    )
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