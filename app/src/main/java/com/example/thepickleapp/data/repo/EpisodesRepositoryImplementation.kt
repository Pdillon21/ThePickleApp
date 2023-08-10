package com.example.thepickleapp.data.repo

import com.example.thepickleapp.data.dao.episode.EpisodeResponseContainer
import com.example.thepickleapp.data.remote.api.RickAndMortyApi
import com.example.thepickleapp.data.result_wrapper.PickleRequestResult
import com.example.thepickleapp.domain.repo.EpisodesRepository
import java.io.IOException
import java.lang.Exception

class EpisodesRepositoryImplementation(
    private val rickAndMortyApi: RickAndMortyApi
) : EpisodesRepository {

    override suspend fun getEpisodes(
        name: String?,
        episode: String?,
        page: Int
    ): PickleRequestResult<EpisodeResponseContainer> {
        return try {
            val request = rickAndMortyApi.getEpisodes(
                name = name,
                episode = episode,
                page = page
            )
            if (request.isSuccessful) {
                PickleRequestResult.success(request.body())
            } else {
                PickleRequestResult.serverError(
                    errorMessage = request.errorBody().toString()
                )
            }
        } catch (e: IOException) {
            PickleRequestResult.connectionError(errorMessage = "Please check your connection")
        } catch (e: Exception) {
            PickleRequestResult.serverError(errorMessage = e.message)
        }
    }
}