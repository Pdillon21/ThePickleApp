package com.example.thepickleapp.data.dao.character

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LocationDao(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)
