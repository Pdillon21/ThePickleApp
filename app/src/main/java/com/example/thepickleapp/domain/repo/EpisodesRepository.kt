package com.example.thepickleapp.domain.repo

import com.example.thepickleapp.data.dao.episode.EpisodeResponseContainer
import com.example.thepickleapp.data.result_wrapper.PickleRequestResult

interface EpisodesRepository {
    suspend fun getEpisodes(): PickleRequestResult<EpisodeResponseContainer>
}