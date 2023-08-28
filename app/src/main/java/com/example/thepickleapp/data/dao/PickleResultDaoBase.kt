package com.example.thepickleapp.data.dao

import androidx.annotation.Keep
import com.example.thepickleapp.data.dao.character.LocationShortDao
import com.google.gson.annotations.SerializedName

@Keep
open class PickleResultBase

@Keep
open class PickleResultDaoBase(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
) : PickleResultBase()

@Keep
data class CharacterDao(
    val characterId: Int?,
    val characterName: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("origin")
    val origin: LocationShortDao?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("location")
    val location: LocationShortDao?,
    @SerializedName("episode")
    val episode: List<String>?
) : PickleResultDaoBase(
    id = characterId,
    name = characterName
)

@Keep
data class LocationDao(
    val locationId: Int?,
    val locationName: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("dimension")
    val dimension: String?,
    @SerializedName("residents")
    val residents: List<String>?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("created")
    val created: String?
) : PickleResultDaoBase(
    id = locationId,
    name = locationName
)

@Keep
data class EpisodeDao(
    val episodeId: Int?,
    val episodeName: String?,
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode")
    val episode: String?,
    @SerializedName("characters")
    val characters: List<String>?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("created")
    val created: String?
) : PickleResultDaoBase(
    id = episodeId,
    name = episodeName
)

data class PagingError(
    val errorTitle: String?,
    val errorMessage: String?
) : PickleResultBase()



