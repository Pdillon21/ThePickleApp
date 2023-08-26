package com.example.thepickleapp.presentation.common_views.list_cells

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepickleapp.R
import com.example.thepickleapp.data.dao.EpisodeDao
import com.example.thepickleapp.presentation.common_views.general.DataPill
import com.example.thepickleapp.presentation.common_views.general.ElevatedContainer
import com.example.thepickleapp.presentation.common_views.utils.TextUtils
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun EpisodeColumnCell(item: EpisodeDao) {
    ElevatedContainer(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = pickleAppColors().episodesAccent,
        bottomElevation = 2.dp,
        sideElevation = 2.dp
    ) {
        val episodeDataInitials = if (!item.episode.isNullOrEmpty()) {
            TextUtils.getEpisodeDataInitials(item.episode)
        } else {
            ""
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-36).dp, x = (-10).dp),
            text = episodeDataInitials,
            color = pickleAppColors().onSurface.copy(alpha = 0.1f),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            fontSize = 96.sp,
            overflow = TextOverflow.Clip,
            maxLines = 1
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            EpisodeName(item.name)
            Spacer(modifier = Modifier.height(4.dp))
            EpisodeData(item.episode)
            Spacer(modifier = Modifier.height(16.dp))
            AiredData(item.airDate)
        }
    }
}

@Composable
fun AiredData(airDate: String?) {
    Row {
        Text(
            text = stringResource(R.string.aired_legend_capitalized),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = pickleAppColors().onSurface
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = airDate ?: stringResource(id = R.string.unknown_capitalized),
            fontSize = 12.sp,
            color = pickleAppColors().onSurface
        )
    }
}

@Composable
fun EpisodeData(episode: String?) {
    val listOfData: List<String> = if (episode.isNullOrEmpty()) {
        listOf(stringResource(id = R.string.unknown_capitalized))
    } else {
        //ToDo pass this logic to a single functin in TextUtils
        TextUtils.getEpisodeDataInitials(episode)
            .replace("E", "splitEpisode ")
            .replace("S", "Season ")
            .split("split")
    }
    Row(
        horizontalArrangement = Arrangement
            .spacedBy(
                space = 2.dp,
                alignment = Alignment.Start
            )
    ) {
        for (data in listOfData) {
            val color: Color = if (
                listOfData.size != 1
            ) {
                if (data.contains("Season")) {
                    pickleAppColors().deadAccent
                } else {
                    pickleAppColors().onSurface
                }
            } else {
                pickleAppColors().unknownAccent
            }
            DataPill(
                modifier = Modifier.wrapContentSize(),
                color = color,
                textColor = pickleAppColors().onSurface,
                text = data
            )
        }
    }
}

@Composable
fun EpisodeName(name: String?) {
    Text(
        text = name ?: stringResource(id = R.string.unknown_capitalized),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = pickleAppColors().onSurface,
        maxLines = 3
    )
}


@Preview
@Composable
fun PreviewEpisodeColumnCell() {
    EpisodeColumnCell(
        item = EpisodeDao(
            episodeId = 1,
            episodeName = "Pilot",
            airDate = "December 2, 2013",
            episode = "S01E01",
            characters = listOf(
                "https://rickandmortyapi.com/api/character/1",
                "https://rickandmortyapi.com/api/character/2",
            ),
            url = "https://rickandmortyapi.com/api/episode/1",
            created = "2017-11-10T12:56:33.798Z"
        )
    )
}