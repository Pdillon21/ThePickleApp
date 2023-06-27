package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.common_views.PickleChip
import com.example.thepickleapp.presentation.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.main_screen.search.state.changeGender
import com.example.thepickleapp.presentation.main_screen.search.state.changeSpecies
import com.example.thepickleapp.presentation.main_screen.search.state.changeStatus
import com.example.thepickleapp.presentation.main_screen.search.state.changeType
import com.example.thepickleapp.presentation.main_screen.search.utils.CharacterGender
import com.example.thepickleapp.presentation.main_screen.search.utils.CharacterStatus

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CharactersExtraFiltersContainer(
    extraFiltersData: ExtraFiltersData,
    changeExtraFiltersData: (ExtraFiltersData) -> Unit
) {
    if (extraFiltersData is ExtraFiltersData.CharacterExtraFilters?) {
        Column() {
            Text(text = "Status: ${extraFiltersData.status ?: ""}")
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                PickleChip(
                    iconResource = R.drawable.sentiment_very_satisfied,
                    text = CharacterStatus.alive,
                    isSelected = (extraFiltersData.status ?: "") == CharacterStatus.alive,
                    contentDescription = CharacterStatus.alive
                ) {
                    if (extraFiltersData.status == CharacterStatus.alive) {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = CharacterStatus.alive))
                    }
                }
                PickleChip(
                    iconResource = R.drawable.sentiment_very_dissatisfied,
                    text = CharacterStatus.dead,
                    isSelected = extraFiltersData.status == CharacterStatus.dead,
                    contentDescription = CharacterStatus.dead
                ) {
                    if (extraFiltersData.status == CharacterStatus.dead) {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = CharacterStatus.dead))
                    }
                }
                PickleChip(
                    iconResource = R.drawable.sentiment_neutral,
                    text = CharacterStatus.unknown,
                    isSelected = extraFiltersData.status == CharacterStatus.unknown,
                    contentDescription = CharacterStatus.unknown
                ) {
                    if (extraFiltersData.status == CharacterStatus.unknown) {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = CharacterStatus.unknown))
                    }
                }
            }

            Text(text = "Gender: ${extraFiltersData.gender ?: ""}")
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                PickleChip(
                    iconResource = R.drawable.man,
                    text = CharacterGender.male,
                    isSelected = extraFiltersData.gender == CharacterGender.male,
                    contentDescription = CharacterGender.male
                ) {
                    if (extraFiltersData.gender == CharacterGender.male) {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = CharacterGender.male))
                    }
                }
                PickleChip(
                    iconResource = R.drawable.woman,
                    text = CharacterGender.female,
                    isSelected = extraFiltersData.gender == CharacterGender.female,
                    contentDescription = CharacterGender.female
                ) {
                    if (extraFiltersData.gender == CharacterGender.female) {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = CharacterGender.female))
                    }
                }
                PickleChip(
                    iconResource = R.drawable.cancel_presentation,
                    text = CharacterGender.genderless,
                    isSelected = extraFiltersData.gender == CharacterGender.genderless,
                    contentDescription = CharacterGender.genderless
                ) {
                    if (extraFiltersData.gender == CharacterGender.genderless) {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = CharacterGender.genderless))
                    }
                }
                PickleChip(
                    iconResource = R.drawable.sentiment_neutral,
                    text = CharacterGender.unknown,
                    isSelected = extraFiltersData.gender == CharacterGender.unknown,
                    contentDescription = CharacterGender.unknown
                ) {
                    if (extraFiltersData.gender == CharacterGender.unknown) {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = CharacterGender.unknown))
                    }
                }
            }
            HighlightPickleSearchBar(
                legend = "Species name: ",
                query = extraFiltersData.species ?: ""
            ) { newSpeciesQuery ->
                changeExtraFiltersData(extraFiltersData.changeSpecies(newSpecies = newSpeciesQuery.ifEmpty { null }))
            }
            Spacer(modifier = Modifier.height(8.dp))
            HighlightPickleSearchBar(
                legend = "Character type: ",
                query = extraFiltersData.type ?: ""
            ) { newTypeQuery ->
                changeExtraFiltersData(extraFiltersData.changeType(newType = newTypeQuery.ifEmpty { null }))
            }
        }
    }
}