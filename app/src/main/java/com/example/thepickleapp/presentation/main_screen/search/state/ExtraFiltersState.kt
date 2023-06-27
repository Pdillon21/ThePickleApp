package com.example.thepickleapp.presentation.main_screen.search.state

data class ExtraFiltersState(
    val isExpanded: Boolean,
    val filtersAreApplied: Boolean,
    val extraFiltersData : ExtraFiltersData
)
