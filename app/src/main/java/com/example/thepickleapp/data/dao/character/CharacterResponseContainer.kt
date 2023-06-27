package com.example.thepickleapp.data.dao.character

import androidx.annotation.Keep
import com.example.thepickleapp.data.dao.PickleResultDaoBase.CharacterDao
import com.example.thepickleapp.data.dao.ResponseContainerBase
import com.example.thepickleapp.data.dao.ResultInfo
import com.google.gson.annotations.SerializedName

@Keep
data class CharacterResponseContainer(
    @SerializedName("info")
    val info: ResultInfo,
    @SerializedName("results")
    val results: List<CharacterDao>
) : ResponseContainerBase()
