package com.example.thepickleapp.data.dao

import androidx.annotation.Keep
import com.example.thepickleapp.data.dao.character.LocationShortDao
import com.google.gson.annotations.SerializedName

sealed class PickleResultDaoBase {

    @Keep
    data class CharacterDao(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
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

    ) : PickleResultDaoBase()

    @Keep
    data class LocationDao(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
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
    ) : PickleResultDaoBase()

    @Keep
    data class EpisodeDao(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
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
    ) : PickleResultDaoBase()


}
