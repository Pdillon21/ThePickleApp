package com.example.thepickleapp.domain.utils

import com.example.thepickleapp.data.dao.CharacterDao
import com.example.thepickleapp.data.dao.LocationDao
import com.example.thepickleapp.data.dao.PickleResultBase
import com.example.thepickleapp.data.dao.ResultInfo
import com.example.thepickleapp.data.dao.character.CharacterResponseContainer
import com.example.thepickleapp.presentation.screens.main_screen.search.state.QueryState
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PaginatorTests {

    @Test
    fun `when results are verified and query state is null, new query state is assigned`() {
        val paginator = Paginator
        paginator.verifyResultType(QueryState.getInitialQueryState())
        assertEquals(paginator.currentQueryData, QueryState.getInitialQueryState())
    }

    @Test
    fun `when results are verified and query state is different, paginator returns to initial state`() {
        val paginator = Paginator
        paginator.currentQueryData = QueryState.getInitialQueryState()
        paginator.results = listOf(PickleResultBase(), PickleResultBase())
        paginator.verifyResultType(QueryState.getInitialQueryState().copy(query = "asd"))
        assertEquals(0, paginator.results.size)
        assertEquals(false, paginator.errorOcurred)
        assertEquals(1, paginator.nextPage)
        assertEquals(false, paginator.isFinalPage)
        assertEquals(false, paginator.isPaging)
    }

    @Test
    fun `when success is added, results numbers change and isPaging is set to false`() {
        val paginator = Paginator
        paginator.results = listOf(CharacterDao(), CharacterDao())
        paginator.isPaging = true
        paginator.addSucccess(
            CharacterResponseContainer(
                info = ResultInfo(
                    count = 3,
                    pages = 3,
                    next = "",
                    prev = ""
                ),
                results = listOf(CharacterDao())
            )
        )
        assertEquals(3, paginator.results.size)
        assertEquals(false, paginator.isPaging)
    }

    @Test
    fun `when success is added, but from different Dao, results are overwritten`() {
        val paginator = Paginator
        paginator.results = listOf(LocationDao(), LocationDao())
        paginator.addSucccess(
            CharacterResponseContainer(
                info = ResultInfo(
                    count = 3,
                    pages = 3,
                    next = "",
                    prev = ""
                ),
                results = listOf(CharacterDao())
            )
        )
        assertEquals(1, paginator.results.size)
    }


}