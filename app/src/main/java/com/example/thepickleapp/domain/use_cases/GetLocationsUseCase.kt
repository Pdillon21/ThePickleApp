package com.example.thepickleapp.domain.use_cases

import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus
import com.example.thepickleapp.domain.repo.LocationsRepository
import com.example.thepickleapp.domain.utils.Paginator
import com.example.thepickleapp.presentation.screens.main_screen.MainScreenUiState
import com.example.thepickleapp.presentation.screens.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.screens.main_screen.search.state.QueryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val repository: LocationsRepository,
    paginator: Paginator
) : BaseUseCase(paginator) {

    suspend operator fun invoke(queryData: QueryState?): Flow<MainScreenUiState> = flow {
        verifyResultType(queryData)
        val extraFiltersData = queryData?.extraFiltersState?.extraFiltersData
        if (extraFiltersData is ExtraFiltersData.LocationsExtraFilters?
            && shouldFetch()
        ) {
            setFetching(true)
            emit(getLoadingState())
            val response = repository.getLocations(
                name = queryData?.query,
                type = extraFiltersData?.type,
                dimension = extraFiltersData?.dimension,
                page = paginator.nextPage
            )
            when (response.status) {
                PickleResponseStatus.SUCCESS -> {
                    emit(getSuccessState(response))
                }

                PickleResponseStatus.CONNECTION_ERROR -> {
                    emit(getErrorState(response))
                }

                PickleResponseStatus.SERVER_ERROR -> {
                    emit(getErrorState(response))
                }
            }.also {
                setFetching(false)
            }
        }
    }
}