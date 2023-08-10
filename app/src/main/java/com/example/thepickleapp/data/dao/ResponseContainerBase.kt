package com.example.thepickleapp.data.dao

import androidx.annotation.Keep
import com.example.thepickleapp.data.dao.character.CharacterResponseContainer
import com.example.thepickleapp.data.dao.episode.EpisodeResponseContainer
import com.example.thepickleapp.data.dao.location.LocationResponseContainer

@Keep
open class ResponseContainerBase {

    fun getResponseInfo(): ResultInfo? {
        if (this is CharacterResponseContainer) {
            return this.info
        }
        if (this is EpisodeResponseContainer) {
            return this.info
        }
        if (this is LocationResponseContainer) {
            return this.info
        }
        return null
    }

    fun getResponseResults(): List<PickleResultDaoBase>? {
        if (this is CharacterResponseContainer) {
            return this.results
        }
        if (this is EpisodeResponseContainer) {
            return this.results
        }
        if (this is LocationResponseContainer) {
            return this.results
        }
        return null
    }
}