package com.example.thepickleapp.domain.repo

import com.example.thepickleapp.data.dao.character.CharacterResponseContainer
import com.example.thepickleapp.data.result_wrapper.PickleRequestResult

interface CharacterRepository {

    suspend fun getCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?,
        page : Int
    ): PickleRequestResult<CharacterResponseContainer>
}