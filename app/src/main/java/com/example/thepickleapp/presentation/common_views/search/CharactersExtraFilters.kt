package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.common_views.general.PickleChip
import com.example.thepickleapp.presentation.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.main_screen.search.state.changeGender
import com.example.thepickleapp.presentation.main_screen.search.state.changeSpecies
import com.example.thepickleapp.presentation.main_screen.search.state.changeStatus
import com.example.thepickleapp.presentation.main_screen.search.state.changeType
import com.example.thepickleapp.presentation.main_screen.search.utils.CharacterGender
import com.example.thepickleapp.presentation.main_screen.search.utils.CharacterStatus
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CharactersExtraFiltersContainer(
    extraFiltersData: ExtraFiltersData,
    changeExtraFiltersData: (ExtraFiltersData) -> Unit
) {
    if (extraFiltersData is ExtraFiltersData.CharacterExtraFilters) {
        Column() {
            Text(
                text = stringResource(R.string.status_capitalized)
                        + stringResource(R.string.SPACE)
                        + (extraFiltersData.status ?: stringResource(R.string.EMPTY_STRING))
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                PickleChip(
                    modifier = Modifier.padding(bottom = 8.dp),
                    iconResource = R.drawable.sentiment_very_satisfied,
                    text = CharacterStatus.alive,
                    colorPrimary = pickleAppColors().aliveAccent,
                    colorSecondary = pickleAppColors().onSurface,
                    isSelected = (extraFiltersData.status
                        ?: stringResource(R.string.EMPTY_STRING)) == CharacterStatus.alive
                ) {
                    if (extraFiltersData.status == CharacterStatus.alive) {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = CharacterStatus.alive))
                    }
                }
                PickleChip(
                    modifier = Modifier.padding(bottom = 8.dp),
                    iconResource = R.drawable.sentiment_very_dissatisfied,
                    text = CharacterStatus.dead,
                    isSelected = extraFiltersData.status == CharacterStatus.dead,
                    colorPrimary = pickleAppColors().deadAccent,
                    colorSecondary = pickleAppColors().onSurface,
                ) {
                    if (extraFiltersData.status == CharacterStatus.dead) {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = CharacterStatus.dead))
                    }
                }
                PickleChip(
                    modifier = Modifier.padding(bottom = 8.dp),
                    iconResource = R.drawable.sentiment_neutral,
                    text = CharacterStatus.unknown,
                    isSelected = extraFiltersData.status == CharacterStatus.unknown,
                    colorPrimary = pickleAppColors().unknownAccent,
                    colorSecondary = pickleAppColors().onSurface
                ) {
                    if (extraFiltersData.status == CharacterStatus.unknown) {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeStatus(newStatus = CharacterStatus.unknown))
                    }
                }
            }

            Text(
                text = stringResource(R.string.gender_legend_capitalized)
                        + stringResource(id = R.string.SPACE)
                        + (extraFiltersData.gender ?: stringResource(id = R.string.EMPTY_STRING))
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                PickleChip(
                    modifier = Modifier.padding(bottom = 8.dp),
                    iconResource = R.drawable.man,
                    text = CharacterGender.male,
                    isSelected = extraFiltersData.gender == CharacterGender.male,
                    colorPrimary = pickleAppColors().maleAccent,
                    colorSecondary = pickleAppColors().onSurface
                ) {
                    if (extraFiltersData.gender == CharacterGender.male) {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = CharacterGender.male))
                    }
                }
                PickleChip(
                    modifier = Modifier.padding(bottom = 8.dp),
                    iconResource = R.drawable.woman,
                    text = CharacterGender.female,
                    isSelected = extraFiltersData.gender == CharacterGender.female,
                    colorPrimary = pickleAppColors().femaleAccent,
                    colorSecondary = pickleAppColors().onSurface
                ) {
                    if (extraFiltersData.gender == CharacterGender.female) {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = CharacterGender.female))
                    }
                }
                PickleChip(
                    modifier = Modifier.padding(bottom = 8.dp),
                    iconResource = R.drawable.cancel_presentation,
                    text = CharacterGender.genderless,
                    isSelected = extraFiltersData.gender == CharacterGender.genderless,
                    colorPrimary = pickleAppColors().genderlessAccent,
                    colorSecondary = pickleAppColors().onSurface
                ) {
                    if (extraFiltersData.gender == CharacterGender.genderless) {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = CharacterGender.genderless))
                    }
                }
                PickleChip(
                    modifier = Modifier.padding(bottom = 8.dp),
                    iconResource = R.drawable.sentiment_neutral,
                    text = CharacterGender.unknown,
                    isSelected = extraFiltersData.gender == CharacterGender.unknown,
                    colorPrimary = pickleAppColors().unknownAccent,
                    colorSecondary = pickleAppColors().onSurface
                ) {
                    if (extraFiltersData.gender == CharacterGender.unknown) {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = null))
                    } else {
                        changeExtraFiltersData(extraFiltersData.changeGender(newGender = CharacterGender.unknown))
                    }
                }
            }
            ElevatedSearchBarWithLegend(
                legend = stringResource(R.string.species_name_legend_capitalized),
                query = extraFiltersData.species ?: stringResource(id = R.string.EMPTY_STRING),
                hint = stringResource(R.string.species_name_hint)
            ) { newSpeciesQuery ->
                changeExtraFiltersData(extraFiltersData.changeSpecies(newSpecies = newSpeciesQuery.ifEmpty { null }))
            }
            Spacer(modifier = Modifier.height(8.dp))
            ElevatedSearchBarWithLegend(
                legend = stringResource(R.string.character_type_legende_capitalized),
                query = extraFiltersData.type ?: stringResource(id = R.string.EMPTY_STRING),
                hint = stringResource(R.string.type_of_character_hint)
            ) { newTypeQuery ->
                changeExtraFiltersData(extraFiltersData.changeType(newType = newTypeQuery.ifEmpty { null }))
            }
        }
    }
}