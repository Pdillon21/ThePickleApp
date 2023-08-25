package com.example.thepickleapp.data.dao.location

import androidx.annotation.Keep
import com.example.thepickleapp.data.dao.LocationDao
import com.example.thepickleapp.data.dao.ResponseContainerBase
import com.example.thepickleapp.data.dao.ResultInfo
import com.google.gson.annotations.SerializedName

@Keep
data class LocationResponseContainer(
    @SerializedName("info")
    val info: ResultInfo,
    @SerializedName("results")
    val results: List<LocationDao>
) : ResponseContainerBase()
