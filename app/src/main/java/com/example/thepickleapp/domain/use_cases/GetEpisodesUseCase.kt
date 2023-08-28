package com.example.thepickleapp.domain.use_cases

import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus
import com.example.thepickleapp.domain.repo.EpisodesRepository
import com.example.thepickleapp.domain.utils.Paginator
import com.example.thepickleapp.presentation.screens.main_screen.MainScreenUiState
import com.example.thepickleapp.presentation.screens.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.screens.main_screen.search.state.QueryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository,
    paginator: Paginator
) : BaseUseCase(paginator) {

    suspend operator fun invoke(queryData: QueryState?): Flow<MainScreenUiState> = flow {
        verifyResultType(queryData)
        val extraFiltersData = queryData?.extraFiltersState?.extraFiltersData
        if (extraFiltersData is ExtraFiltersData.EpisodesExtraFilters?
            && shouldFetch()
        ) {
            setFetching(true)
            emit(getLoadingState())
            val response = repository.getEpisodes(
                name = queryData?.query,
                episode = null,
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