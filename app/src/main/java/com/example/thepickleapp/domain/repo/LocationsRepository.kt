package com.example.thepickleapp.domain.repo

import com.example.thepickleapp.data.dao.location.LocationResponseContainer
import com.example.thepickleapp.data.result_wrapper.PickleRequestResult

interface LocationsRepository {

    suspend fun getLocations(
        name: String?,
        type: String?,
        dimension: String?,
        page : Int
    ): PickleRequestResult<LocationResponseContainer>
}