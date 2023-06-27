package com.example.thepickleapp.presentation.main_screen.search.utils

import com.example.thepickleapp.presentation.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.main_screen.search.state.QueryState

object SearchUtils {

    fun getSearchSummaryByState(queryState: QueryState): String {
        return when (queryState.selectedQueryType) {
            SearchType.characters -> getSearchSummaryForCharacters(queryState)
            SearchType.locations -> getSearchSummaryForLocations(queryState)
            SearchType.episodes -> getSearchSummaryForEpisodes(queryState)
            else -> getGenericSearchSummary(queryState)
        }
    }

    private fun getSearchSummaryForCharacters(queryState: QueryState): String {
        if (queryState.extraFiltersState.extraFiltersData is ExtraFiltersData.CharacterExtraFilters) {
            val filtersData = queryState.extraFiltersState.extraFiltersData
            var additions = 0
            var result = "Searching for all ${SearchType.characters}"
            if (!queryState.query.isNullOrEmpty()) {
                result += " named ${queryState.query}"
            }
            if (!filtersData.status.isNullOrEmpty()) {
                result += " that are ${filtersData.status}"
                additions += 1
            }
            if (filtersData.gender != null) {
                if (additions > 0) {
                    result += ", "
                }
                result += filtersData.gender
                additions += 1
            }
            if (filtersData.species != null) {
                if (additions > 0) {
                    result += ", "
                }
                result += "of the ${filtersData.species} species"
            }
            if (filtersData.type != null) {
                result += ", and are ${filtersData.type}"
            }
            return result
        }
        return ""
    }

    private fun getSearchSummaryForLocations(queryState: QueryState): String {
        if (queryState.extraFiltersState.extraFiltersData is ExtraFiltersData.LocationsExtraFilters) {

        }
        return ""
    }

    private fun getSearchSummaryForEpisodes(queryState: QueryState): String {
        if (queryState.extraFiltersState.extraFiltersData is ExtraFiltersData.EpisodesExtraFilters) {

        }
        return ""
    }

    private fun getGenericSearchSummary(queryState: QueryState): String {
        return ""
    }


}