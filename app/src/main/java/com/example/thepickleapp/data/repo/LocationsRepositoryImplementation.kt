package com.example.thepickleapp.data.repo

import com.example.thepickleapp.data.dao.location.LocationResponseContainer
import com.example.thepickleapp.data.remote.api.RickAndMortyApi
import com.example.thepickleapp.data.result_wrapper.PickleRequestResult
import com.example.thepickleapp.domain.repo.LocationsRepository
import java.io.IOException
import java.lang.Exception

class LocationsRepositoryImplementation(
    private val rickAndMortyApi: RickAndMortyApi
) : LocationsRepository {

    override suspend fun getLocations(
        name: String?,
        type: String?,
        dimension: String?,
        page: Int
    ): PickleRequestResult<LocationResponseContainer> {
        return try {
            val request = rickAndMortyApi.getLocations(
                name = name,
                type = type,
                dimension = dimension,
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
            PickleRequestResult.connectionError(errorMessage = e.message)
        } catch (e: Exception) {
            PickleRequestResult.serverError(errorMessage = e.message)
        }
    }

}