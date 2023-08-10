package com.example.thepickleapp.presentation.main_screen.search.state

import com.example.thepickleapp.presentation.main_screen.search.utils.SearchType

data class QueryState(
    val query: String?,
    val selectedQueryType: String,
    val extraFiltersState: ExtraFiltersState
) {

    companion object {
        fun getInitialQueryState(): QueryState {
            return QueryState(
                query = null,
                selectedQueryType = SearchType.characters,
                extraFiltersState = ExtraFiltersState(
                    isExpanded = false,
                    filtersAreApplied = false,
                    extraFiltersData = ExtraFiltersData.CharacterExtraFilters(
                        status = null, gender = null, species = null, type = null
                    )
                )
            )
        }
    }
}
