package com.example.thepickleapp.data.remote.api

import com.example.thepickleapp.data.dao.character.CharacterResponseContainer
import com.example.thepickleapp.data.dao.episode.EpisodeResponseContainer
import com.example.thepickleapp.data.dao.location.LocationResponseContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("species") species: String?,
        @Query("type") type: String?,
        @Query("gender") gender: String?
    ): Response<CharacterResponseContainer>

    @GET("/api/location")
    suspend fun getLocations(
        @Query("name") name: String?,
        @Query("type") type: String?,
        @Query("dimension") dimension: String?
    ): Response<LocationResponseContainer>

    @GET("api/episode")
    suspend fun getEpisodes(
        @Query("name") name: String?,
        @Query("episode") episode: String?
    ): Response<EpisodeResponseContainer>
}