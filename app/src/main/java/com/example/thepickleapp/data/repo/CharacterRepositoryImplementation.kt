package com.example.thepickleapp.data.repo

import com.example.thepickleapp.data.dao.character.CharacterResponseContainer
import com.example.thepickleapp.data.remote.api.RickAndMortyApi
import com.example.thepickleapp.data.result_wrapper.PickleRequestResult
import com.example.thepickleapp.domain.repo.CharacterRepository
import java.io.IOException
import java.lang.Exception

class CharacterRepositoryImplementation(
    private val rickAndMortyApi: RickAndMortyApi
) : CharacterRepository {

    override suspend fun getCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?,
        page : Int
    ): PickleRequestResult<CharacterResponseContainer> {
        return try {
            val request = rickAndMortyApi.getCharacters(
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
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