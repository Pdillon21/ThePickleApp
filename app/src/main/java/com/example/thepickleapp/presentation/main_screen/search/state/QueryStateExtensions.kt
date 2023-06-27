package com.example.thepickleapp.presentation.main_screen.search.state

import com.example.thepickleapp.presentation.main_screen.search.utils.EpisodeFilterType
import com.example.thepickleapp.presentation.main_screen.search.utils.SearchType


fun QueryState.changeQueryInput(searchPrompt: String): QueryState {
    return QueryState(
        query = searchPrompt,
        selectedQueryType = this.selectedQueryType,
        extraFiltersState = this.extraFiltersState
    )
}

fun QueryState.changeExtraFilterData(filtersData: ExtraFiltersData): QueryState {
    return QueryState(
        query = this.query,
        selectedQueryType = this.selectedQueryType,
        extraFiltersState = ExtraFiltersState(
            isExpanded = this.extraFiltersState.isExpanded,
            filtersAreApplied = ExtraFiltersData.areFiltersApplied(filtersData),
            extraFiltersData = filtersData
        )
    )
}

fun QueryState.changeQueryType(newType: String): QueryState {
    if (newType != this.selectedQueryType) {
        return when (newType) {
            SearchType.episodes -> {
                QueryState(
                    query = "",
                    selectedQueryType = newType,
                    extraFiltersState = ExtraFiltersState(
                        isExpanded = this.extraFiltersState.isExpanded,
                        filtersAreApplied = true,
                        extraFiltersData = ExtraFiltersData.EpisodesExtraFilters(
                            EpisodeFilterType.episodeFilterByName
                        )
                    )
                )
            }

            SearchType.locations -> {
                QueryState(
                    query = "",
                    selectedQueryType = newType,
                    extraFiltersState = ExtraFiltersState(
                        isExpanded = this.extraFiltersState.isExpanded,
                        filtersAreApplied = false,
                        extraFiltersData = ExtraFiltersData.LocationsExtraFilters(
                            type = null,
                            dimension = null
                        )
                    )
                )
            }

            SearchType.characters -> {
                QueryState(
                    query = "",
                    selectedQueryType = newType,
                    extraFiltersState = ExtraFiltersState(
                        isExpanded = this.extraFiltersState.isExpanded,
                        filtersAreApplied = false,
                        extraFiltersData = ExtraFiltersData.CharacterExtraFilters(
                            status = null,
                            gender = null,
                            species = null,
                            type = null
                        )
                    )
                )
            }

            else -> {
                QueryState(
                    query = "",
                    selectedQueryType = newType,
                    extraFiltersState = ExtraFiltersState(
                        isExpanded = this.extraFiltersState.isExpanded,
                        filtersAreApplied = false,
                        extraFiltersData = ExtraFiltersData.CharacterExtraFilters(
                            status = null,
                            gender = null,
                            species = null,
                            type = null
                        )
                    )
                )
            }
        }
    } else {
        return this
    }
}

fun QueryState.expandExtraFilters(): QueryState {
    return QueryState(
        query = this.query,
        selectedQueryType = this.selectedQueryType,
        extraFiltersState = ExtraFiltersState(
            isExpanded = true,
            filtersAreApplied = this.extraFiltersState.filtersAreApplied,
            extraFiltersData = this.extraFiltersState.extraFiltersData
        )
    )
}

fun QueryState.hideExtraFilters(): QueryState {
    return QueryState(
        query = this.query,
        selectedQueryType = this.selectedQueryType,
        extraFiltersState = ExtraFiltersState(
            isExpanded = false,
            filtersAreApplied = this.extraFiltersState.filtersAreApplied,
            extraFiltersData = this.extraFiltersState.extraFiltersData
        )
    )
}

fun QueryState.getParamStringValue(paramKey: String): String? {
    if (paramKey == "name") {
        if (this.query.isNullOrEmpty()) {
            return null
        }
        return this.query
    }
    when (val extraFilters = this.extraFiltersState.extraFiltersData) {
        is ExtraFiltersData.CharacterExtraFilters -> {
            when (paramKey) {
                "status" -> return extraFilters.status
                "species" -> return extraFilters.species
                "type" -> return extraFilters.type
                "gender" -> return extraFilters.gender
            }
        }

        is ExtraFiltersData.EpisodesExtraFilters -> {
            when (paramKey) {
                "code" -> this.query
            }
        }

        is ExtraFiltersData.LocationsExtraFilters -> {
            when (paramKey) {
                "type" -> extraFilters.type
                "dimension" -> extraFilters.dimension
            }
        }
    }

    return null
}