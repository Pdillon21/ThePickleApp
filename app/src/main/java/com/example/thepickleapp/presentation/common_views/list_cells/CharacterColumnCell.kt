package com.example.thepickleapp.presentation.common_views.list_cells

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepickleapp.R
import com.example.thepickleapp.data.dao.CharacterDao
import com.example.thepickleapp.data.dao.character.LocationShortDao
import com.example.thepickleapp.presentation.common_views.general.PickleImageView
import com.example.thepickleapp.presentation.common_views.utils.TextUtils
import com.example.thepickleapp.presentation.common_views.general.DataPill
import com.example.thepickleapp.presentation.common_views.general.ElevatedContainer
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun CharacterColumnCell(singleItem: CharacterDao) {

    ElevatedContainer(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = pickleAppColors().charactersAccent,
        bottomElevation = 2.dp,
        sideElevation = 2.dp
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-36).dp, x = 12.dp),
            text = TextUtils.getInitialsAndNumbers(
                singleItem.name ?: stringResource(R.string.unknown_capitalized)
            ),
            color = pickleAppColors().onSurface.copy(alpha = 0.1f),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            fontSize = 96.sp,
            overflow = TextOverflow.Clip,
            maxLines = 1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            CharacterImageView(singleItem.image)
            CharacterData(singleItem)
        }
    }

}

@Composable
fun CharacterImageView(image: String?) {
    val imageWidth = LocalConfiguration.current.screenWidthDp / 2.4
    PickleImageView(
        modifier = Modifier
            .width(imageWidth.dp)
            .aspectRatio(1f)
            .padding(8.dp), url = image
    )
}

@Composable
fun CharacterData(singleItem: CharacterDao) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
    ) {
        DataPill(
            modifier = Modifier.wrapContentSize(),
            color = pickleAppColors().genderlessAccent,
            textColor = pickleAppColors().onSurface,
            text = singleItem.species ?: stringResource(R.string.unknown_capitalized)
        )
        Spacer(modifier = Modifier.height(6.dp))
        CharacterName(singleItem.name ?: stringResource(R.string.unknown_capitalized))
        Spacer(modifier = Modifier.height(4.dp))
        CharacterLocationData(
            locationFrom = singleItem.origin?.name ?: stringResource(R.string.unknown_capitalized),
            locationIn = singleItem.location?.name ?: stringResource(R.string.unknown_capitalized)
        )
        Spacer(modifier = Modifier.height(16.dp))
        CharacterAppearances(singleItem.episode)
    }
}

@Composable
fun CharacterName(name: String) {
    Text(
        text = name,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = pickleAppColors().onSurface,
        maxLines = 3
    )
}

@Composable
fun CharacterAppearances(episode: List<String>?) {
    Row() {
        val episodes = episode?.size
        Text(
            text = episodes?.toString() ?: stringResource(R.string.unknown_capitalized),
            fontSize = 14.sp,
            color = pickleAppColors().onSurface,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text =
            if (episodes == 1) stringResource(R.string.appearance_single)
            else stringResource(R.string.appearances_plural),
            fontSize = 14.sp,
            color = pickleAppColors().onSurface
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CharacterLocationData(locationFrom: String, locationIn: String) {
    FlowRow(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.Start)
    ) {
        Text(
            text = stringResource(R.string.from),
            fontSize = 14.sp,
            color = pickleAppColors().onSurface
        )
        DataPill(
            modifier = Modifier
                .widthIn(0.dp, 100.dp)
                .wrapContentSize(),
            text = locationFrom,
            color = pickleAppColors().locationsAccent,
            textColor = pickleAppColors().onSurface
        )
        Text(
            text = stringResource(R.string.comma_in),
            fontSize = 14.sp,
            color = pickleAppColors().onSurface
        )
        DataPill(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 2.dp),
            text = locationIn,
            color = pickleAppColors().locationsAccent,
            textColor = pickleAppColors().onSurface
        )
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 300)
@Composable
fun PreviewCharacterColumnCell() {
    CharacterColumnCell(
        singleItem = CharacterDao(
            characterId = 1,
            characterName = "Pablo",
            status = "Unknown",
            species = "Human",
            type = "Bot",
            gender = "Male",
            origin = LocationShortDao("Munro", "Munro.com"),
            location = LocationShortDao("Barcelona", "Barcelona.com"),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf("1"),
            url = "",
            created = "2017-11-10T12:42:04.162Z"
        )
    )
}
