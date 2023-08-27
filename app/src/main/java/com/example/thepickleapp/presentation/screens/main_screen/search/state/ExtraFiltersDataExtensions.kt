package com.example.thepickleapp.presentation.screens.main_screen.search.state

fun ExtraFiltersData.CharacterExtraFilters.changeStatus(
    newStatus: String?,
): ExtraFiltersData.CharacterExtraFilters {
    return ExtraFiltersData.CharacterExtraFilters(
        status = newStatus,
        gender = this.gender,
        species = this.species,
        type = this.type
    )
}

fun ExtraFiltersData.CharacterExtraFilters.changeGender(
    newGender: String?,
): ExtraFiltersData.CharacterExtraFilters {
    return ExtraFiltersData.CharacterExtraFilters(
        status = this.status,
        gender = newGender,
        species = this.species,
        type = this.type
    )
}

fun ExtraFiltersData.CharacterExtraFilters.changeSpecies(
    newSpecies: String?,
): ExtraFiltersData.CharacterExtraFilters {
    return ExtraFiltersData.CharacterExtraFilters(
        status = this.status,
        gender = this.gender,
        species = newSpecies,
        type = this.type
    )
}

fun ExtraFiltersData.CharacterExtraFilters.changeType(
    newType: String?,
): ExtraFiltersData.CharacterExtraFilters {
    return ExtraFiltersData.CharacterExtraFilters(
        status = this.status,
        gender = this.gender,
        species = this.species,
        type = newType
    )
}

fun ExtraFiltersData.LocationsExtraFilters.changeType(
    newType: String?,
): ExtraFiltersData.LocationsExtraFilters {
    return ExtraFiltersData.LocationsExtraFilters(
        type = newType,
        dimension = this.dimension
    )
}

fun ExtraFiltersData.LocationsExtraFilters.changeDimension(
    newDimension: String?,
): ExtraFiltersData.LocationsExtraFilters {
    return ExtraFiltersData.LocationsExtraFilters(
        type = this.type,
        dimension = newDimension
    )
}

fun ExtraFiltersData.EpisodesExtraFilters.changeQueryType(
    newQueryType: String,
): ExtraFiltersData.EpisodesExtraFilters {
    return ExtraFiltersData.EpisodesExtraFilters(
        episodeQueryType = newQueryType
    )
}