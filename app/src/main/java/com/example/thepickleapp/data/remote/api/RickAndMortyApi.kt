package com.example.thepickleapp.data.remote.api

import com.example.thepickleapp.data.dao.character.CharacterResultContainer
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(): Response<CharacterResultContainer>

}