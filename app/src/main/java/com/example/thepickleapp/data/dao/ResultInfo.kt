package com.example.thepickleapp.data.dao

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResultInfo(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)
