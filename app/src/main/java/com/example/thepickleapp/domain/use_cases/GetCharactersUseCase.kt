package com.example.thepickleapp.domain.use_cases

import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus
import com.example.thepickleapp.domain.repo.CharacterRepository
import com.example.thepickleapp.domain.utils.Paginator
import com.example.thepickleapp.presentation.screens.main_screen.MainScreenUiState
import com.example.thepickleapp.presentation.screens.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.screens.main_screen.search.state.QueryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository,
    paginator: Paginator
) : BaseUseCase(paginator) {

    suspend operator fun invoke(queryData: QueryState?): Flow<MainScreenUiState> = flow {

        verifyResultType(queryData)
        val extraFiltersData = queryData?.extraFiltersState?.extraFiltersData
        if (extraFiltersData is ExtraFiltersData.CharacterExtraFilters?
            && shouldFetch()
        ) {
            setFetching(true)
            emit(getLoadingState())
            val response = repository.getCharacters(
                name = queryData?.query.let { if (!it.isNullOrEmpty()) it else null },
                status = extraFiltersData?.status.let { if (!it.isNullOrEmpty()) it else null },
                species = extraFiltersData?.species.let { if (!it.isNullOrEmpty()) it else null },
                type = extraFiltersData?.type.let { if (!it.isNullOrEmpty()) it else null },
                gender = extraFiltersData?.gender.let { if (!it.isNullOrEmpty()) it else null },
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
