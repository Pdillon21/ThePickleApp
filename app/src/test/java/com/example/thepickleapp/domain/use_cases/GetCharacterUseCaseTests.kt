package com.example.thepickleapp.domain.use_cases

import app.cash.turbine.test
import com.example.thepickleapp.data.repo.CharacterRepositoryImplementation
import com.example.thepickleapp.domain.repo.CharacterRepository
import com.example.thepickleapp.domain.utils.Paginator
import com.example.thepickleapp.presentation.screens.main_screen.MainScreenUiState
import com.example.thepickleapp.presentation.screens.main_screen.search.state.QueryState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class GetCharacterUseCaseTests {
    private lateinit var characterRepository: CharacterRepository
    private var paginator = Mockito.mock(Paginator::class.java)

    @Before
    fun setup() {
        characterRepository = Mockito.mock(CharacterRepositoryImplementation::class.java)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `when reposityor usecase is invoked, loading is emmited`() {
        CoroutineScope(Dispatchers.IO).launch {
            GetCharactersUseCase(characterRepository, paginator)
                .invoke(QueryState.getInitialQueryState())
                .test(timeout = Duration.parse("15s")) {
                    assertEquals(MainScreenUiState.Loading::class.java, awaitItem()::class.java)
                    awaitComplete()
                }
        }
    }
}