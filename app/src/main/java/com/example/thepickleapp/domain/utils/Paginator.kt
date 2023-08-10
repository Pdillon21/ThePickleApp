package com.example.thepickleapp.domain.utils

import com.example.thepickleapp.data.dao.PickleResultDaoBase
import com.example.thepickleapp.data.dao.ResponseContainerBase
import com.example.thepickleapp.presentation.main_screen.search.state.QueryState

object Paginator {

    var nextPage: Int = 1
    private var totalPages: Int? = null
    var isFinalPage: Boolean = false
    var isPaging: Boolean = false
    var results: List<PickleResultDaoBase> = emptyList()
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
            results = if (results[results.size-1]::class == newResults[0]::class){
                results + newResults
            } else {
                newResults
            }
        }
    }

    fun verifyResultType(queryData: QueryState?) {
        if (currentQueryData == null) {
            currentQueryData = queryData
        }
        if (currentQueryData != queryData){
            clearResultsData()
            currentQueryData = queryData
        }
    }

    private fun clearResultsData() {
        nextPage = 1
        totalPages = null
        isFinalPage = false
        isPaging = false
        results = emptyList()
    }
}