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
    val characterId: Int? = null,
    val characterName: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("species")
    val species: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("origin")
    val origin: LocationShortDao? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("location")
    val location: LocationShortDao? = null,
    @SerializedName("episode")
    val episode: List<String>? = null
) : PickleResultDaoBase(
    id = characterId,
    name = characterName
)

@Keep
data class LocationDao(
    val locationId: Int? = null,
    val locationName: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("dimension")
    val dimension: String? = null,
    @SerializedName("residents")
    val residents: List<String>? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("created")
    val created: String? = null
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



