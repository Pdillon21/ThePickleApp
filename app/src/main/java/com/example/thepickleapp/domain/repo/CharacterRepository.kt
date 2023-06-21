package com.example.thepickleapp.domain.repo

import com.example.thepickleapp.data.dao.character.CharacterResultContainer
import com.example.thepickleapp.data.result_wrapper.PickleRequestResult

interface CharacterRepository {

    suspend fun getAllCharacters(): PickleRequestResult<CharacterResultContainer>
}