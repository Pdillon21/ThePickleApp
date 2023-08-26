package com.example.thepickleapp.presentation.ui.theme


fun pickleAppColors(
    // isSystemDarkTHeme: Boolean
): PickleAppColorColection {
    return pickleAppLightColors()
    //DarkThemeImplementation with If
}

fun pickleAppLightColors(): PickleAppColorColection {
    return PickleAppColorColection(
        surface = FloralWhite,
        onSurface = CharlestonGreen,
        searchBarSurface = LemonChiffon,
        onSearchBarHint = Silver,
        typeFilterSurface = PalePurple,
        extraFiltersSurface = AliceBlue,
        charactersAccent = TeaGreen,
        locationsAccent = Water,
        episodesAccent = Mauve,
        buttonAccent = Mindaro,
        aliveAccent = HoneyDew,
        deadAccent = LavenderBlush,
        unknownAccent = AntiFlashWhite,
        maleAccent = LavenderWeb,
        femaleAccent = PalePurple,
        genderlessAccent = Magnolia
    )
}