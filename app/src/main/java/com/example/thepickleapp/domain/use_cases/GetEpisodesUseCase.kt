package com.example.thepickleapp.domain.use_cases

import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus
import com.example.thepickleapp.domain.repo.EpisodesRepository
import com.example.thepickleapp.domain.utils.Paginator
import com.example.thepickleapp.presentation.main_screen.MainScreenUiState
import com.example.thepickleapp.presentation.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.main_screen.search.state.QueryState
import com.example.thepickleapp.presentation.main_screen.search.utils.EpisodeFilterType
import com.example.thepickleapp.presentation.utils.ErrorData
import com.example.thepickleapp.presentation.utils.ErrorUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository,
    private val paginator: Paginator = Paginator
) {
    private var isFetching : Boolean = false

    suspend operator fun invoke(queryData: QueryState?): Flow<MainScreenUiState> = flow {
        paginator.verifyResultType(queryData)
        if (!paginator.isFinalPage && !paginator.isPaging && !isFetching) {
            isFetching = true
            if (paginator.results.isNotEmpty()) {
                paginator.isPaging = true
                emit(
                    MainScreenUiState.Success(
                        data = paginator.results,
                        isPaging = paginator.isPaging,
                        isLastPage = paginator.isFinalPage
                    )
                )
            } else {
                emit(
                    MainScreenUiState.Loading
                )
            }
            var queryType: String? = "Name"
            val extraData = queryData?.extraFiltersState?.extraFiltersData
            if (extraData is ExtraFiltersData.EpisodesExtraFilters) {
                queryType = extraData.episodeQueryType
            }
            val response = if (queryType == EpisodeFilterType.episodeFilterByName) {
                repository.getEpisodes(
                    name = queryData?.query,
                    episode = null,
                    page = paginator.nextPage
                )
            } else {
                repository.getEpisodes(
                    name = null,
                    episode = queryData?.query,
                    page = paginator.nextPage
                )
            }
            when (response.status) {
                PickleResponseStatus.SUCCESS -> {
                    isFetching = false
                    if (response.data != null) {
                        paginator.addSucccess(
                            response.data
                        )
                        emit(
                            MainScreenUiState.Success(
                                data = paginator.results,
                                isPaging = paginator.isPaging,
                                isLastPage = paginator.isFinalPage
                            )
                        )
                    } else {
                        MainScreenUiState.Error(errorData = ErrorUtils.getDefaultErrorData())
                    }
                }

                PickleResponseStatus.CONNECTION_ERROR -> {
                    isFetching = false
                    emit(
                        MainScreenUiState.Error(
                            errorData = ErrorData(
                                errorTitle = "Ups! Something went wrong!",
                                errorMessage = "Try checking your Interdimentional Internet connection"
                            )
                        )
                    )
                }

                PickleResponseStatus.SERVER_ERROR -> {
                    isFetching = false
                    emit(
                        MainScreenUiState.Error(
                            errorData = ErrorData(
                                errorTitle = "Ups! Something went wrong!",
                                errorMessage = "We couldn't perform this action for this universe."
                            )
                        )
                    )
                }
            }
        }
    }
}