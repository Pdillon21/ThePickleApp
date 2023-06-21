package com.example.thepickleapp.data.repo

import android.app.Application
import com.example.thepickleapp.data.dao.character.CharacterResultContainer
import com.example.thepickleapp.data.remote.api.RickAndMortyApi
import com.example.thepickleapp.data.result_wrapper.PickleRequestResult
import com.example.thepickleapp.domain.repo.CharacterRepository
import java.io.IOException
import java.lang.Exception

class CharacterRepositoryImplementation(
    private val rickAndMortyApi: RickAndMortyApi,
    private val appContext: Application
) : CharacterRepository {

    override suspend fun getAllCharacters(): PickleRequestResult<CharacterResultContainer> {
        return try {
            val request = rickAndMortyApi.getCharacters()
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