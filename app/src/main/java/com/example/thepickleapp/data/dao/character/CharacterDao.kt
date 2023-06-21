package com.example.thepickleapp.data.dao.character

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

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
    @SerializedName ("origin")
    val origin : LocationDao?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("location")
    val location: LocationDao?,
    @SerializedName("episode")
    val episode: List<String>?

)
