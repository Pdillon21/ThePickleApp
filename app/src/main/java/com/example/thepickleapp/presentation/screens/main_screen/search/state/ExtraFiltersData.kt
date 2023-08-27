package com.example.thepickleapp.presentation.screens.main_screen.search.state

sealed class ExtraFiltersData {
    data class CharacterExtraFilters(
        val status: String?,
        val gender: String?,
        val species: String?,
        val type: String?
    ) : ExtraFiltersData()

    data class LocationsExtraFilters(
        val type: String?,
        val dimension: String?
    ) : ExtraFiltersData()

    data class EpisodesExtraFilters(
        val episodeQueryType: String?
    ) : ExtraFiltersData()

    companion object {
        fun areFiltersApplied(data: ExtraFiltersData?): Boolean {
            if (data == null) {
                return false
            }
            when (data) {
                is CharacterExtraFilters -> {
                    if (
                        !data.gender.isNullOrEmpty()
                        || !data.species.isNullOrEmpty()
                        || !data.type.isNullOrEmpty()
                        || !data.status.isNullOrEmpty()
                    ) {
                        return true
                    }
                }

                is EpisodesExtraFilters -> {
                    return true
                }

                is LocationsExtraFilters -> {
                    if (
                        !data.type.isNullOrEmpty()
                        || !data.dimension.isNullOrEmpty()
                    ) {
                        return true
                    }
                }
            }
            return false
        }
    }
}
