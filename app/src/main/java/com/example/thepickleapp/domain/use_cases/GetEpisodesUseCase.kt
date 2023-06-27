package com.example.thepickleapp.domain.use_cases

import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus
import com.example.thepickleapp.domain.repo.EpisodesRepository
import com.example.thepickleapp.presentation.main_screen.MainScreenUiState
import com.example.thepickleapp.presentation.utils.ErrorData
import com.example.thepickleapp.presentation.utils.ErrorUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {
    suspend operator fun invoke(): Flow<MainScreenUiState> = flow {
        emit(MainScreenUiState.Loading)
        val response = repository.getEpisodes()
        when (response.status) {
            PickleResponseStatus.SUCCESS -> {
                if (response.data != null) {
                    emit(MainScreenUiState.Success(data = response.data))
                } else {
                    MainScreenUiState.Error(errorData = ErrorUtils.getDefaultErrorData())
                }
            }

            PickleResponseStatus.CONNECTION_ERROR -> {
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