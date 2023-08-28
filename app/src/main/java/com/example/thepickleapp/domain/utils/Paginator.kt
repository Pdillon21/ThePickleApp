package com.example.thepickleapp.domain.utils

import com.example.thepickleapp.data.dao.PagingError
import com.example.thepickleapp.data.dao.PickleResultBase
import com.example.thepickleapp.data.dao.ResponseContainerBase
import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus
import com.example.thepickleapp.presentation.screens.main_screen.search.state.QueryState

object Paginator {

    var nextPage: Int = 1
    private var totalPages: Int? = null
    var isFinalPage: Boolean = false
    var isPaging: Boolean = false
    var errorOcurred: Boolean = false
    var results: List<PickleResultBase> = emptyList()
    var currentQueryData: QueryState? = null

    fun addSucccess(data: ResponseContainerBase) {
        isPaging = false
        totalPages = data.getResponseInfo()?.pages
        totalPages?.let { total ->
            if (nextPage + 1 > total) {
                isFinalPage = true
            } else {
                nextPage += 1
            }
        }
        data.getResponseResults()?.let { newResults ->
            results = if (results.isNotEmpty()) {
                if (results[results.size - 1]::class == newResults[0]::class) {
                    results + newResults
                } else {
                    newResults
                }
            } else {
                newResults
            }
        }
    }

    fun verifyResultType(queryData: QueryState?) {
        if (currentQueryData == null) {
            currentQueryData = queryData
        }
        if (currentQueryData != queryData) {
            clearResultsData()
            currentQueryData = queryData
        }
    }

    private fun clearResultsData() {
        nextPage = 1
        totalPages = null
        isFinalPage = false
        isPaging = false
        errorOcurred = false
        results = emptyList()
    }

    fun addError(
        status: PickleResponseStatus,
        errorMessage: String?
    ) {
        isPaging = false
        isFinalPage = false
        errorOcurred = true
        val pagingError = getPagingErrorForStatus(status, errorMessage)
        pagingError?.let {
            results = results + pagingError
        }

    }

    private fun getPagingErrorForStatus(
        status: PickleResponseStatus,
        errorMessage: String?
    ): PickleResultBase? {
        return when (status) {
            PickleResponseStatus.SERVER_ERROR -> {
                PagingError(
                    errorTitle = "Ups, there was a problem!",
                    errorMessage = errorMessage
                        ?: "We couldn't perform this action. Try again later."
                )
            }

            PickleResponseStatus.CONNECTION_ERROR -> {
                PagingError(
                    errorTitle = "Ups, there was a problem!",
                    errorMessage = errorMessage
                        ?: "Verify your interdimensional internet connection."
                )
            }

            else -> {
                null
            }
        }
    }

    fun pagingEnabled(): Boolean {
       return !isFinalPage && !isPaging && !errorOcurred
    }
}