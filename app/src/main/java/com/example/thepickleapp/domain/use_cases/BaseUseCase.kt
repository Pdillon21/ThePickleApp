package com.example.thepickleapp.domain.use_cases

import com.example.thepickleapp.data.dao.ResponseContainerBase
import com.example.thepickleapp.data.result_wrapper.PickleRequestResult
import com.example.thepickleapp.domain.utils.Paginator
import com.example.thepickleapp.presentation.screens.main_screen.MainScreenUiState
import com.example.thepickleapp.presentation.screens.main_screen.search.state.QueryState
import com.example.thepickleapp.presentation.utils.ErrorData
import com.example.thepickleapp.presentation.utils.ErrorUtils

open class BaseUseCase(
    val paginator : Paginator
) {

    private var isFetching: Boolean = false

    protected fun shouldFetch(): Boolean {
        return paginator.pagingEnabled()
                && !isFetching
    }

    protected fun verifyResultType(queryState: QueryState?) {
        paginator.verifyResultType(queryState)
    }

    protected fun setFetching(fetching: Boolean) {
        isFetching = fetching
    }

    protected fun getLoadingState(): MainScreenUiState {
        return if (paginator.results.isNotEmpty()) {
            paginator.isPaging = true
            MainScreenUiState.Success(
                data = paginator.results,
                isPaging = paginator.isPaging,
                isLastPage = paginator.isFinalPage
            )

        } else {
            MainScreenUiState.Loading
        }
    }

    protected fun getSuccessState(
        response: PickleRequestResult<ResponseContainerBase>
    ): MainScreenUiState {
        return if (response.data != null) {
            paginator.addSucccess(
                response.data
            )
            MainScreenUiState.Success(
                data = paginator.results,
                isPaging = paginator.isPaging,
                isLastPage = paginator.isFinalPage
            )
        } else {
            MainScreenUiState.Error(errorData = ErrorUtils.getDefaultErrorData())
        }
    }

    protected fun getErrorState(response: PickleRequestResult<ResponseContainerBase>): MainScreenUiState {
        if (paginator.results.isEmpty()) {
            return MainScreenUiState.Error(
                errorData = ErrorData(
                    errorTitle = "Ups! Something went wrong!",
                    errorMessage = response.errorMessage
                )
            )

        } else {
            paginator.addError(
                response.status,
                response.errorMessage
            )
            return MainScreenUiState.Success(
                data = paginator.results,
                isPaging = paginator.isPaging,
                isLastPage = paginator.isFinalPage
            )

        }
    }
}