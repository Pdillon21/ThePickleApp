package com.example.thepickleapp.data.dao.episode

import androidx.annotation.Keep
import com.example.thepickleapp.data.dao.PickleResultDaoBase.EpisodeDao
import com.example.thepickleapp.data.dao.ResponseContainerBase
import com.example.thepickleapp.data.dao.ResultInfo
import com.google.gson.annotations.SerializedName

@Keep
data class EpisodeResponseContainer(
    @SerializedName("info")
    val info: ResultInfo,
    @SerializedName("results")
    val results: List<EpisodeDao>
) : ResponseContainerBase()
