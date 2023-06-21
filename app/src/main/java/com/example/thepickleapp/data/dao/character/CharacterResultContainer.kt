package com.example.thepickleapp.data.dao.character

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CharacterResultContainer(
    @SerializedName("info")
    val info: ResultInfo,
    @SerializedName("results")
    val results: List<CharacterDao>
)
