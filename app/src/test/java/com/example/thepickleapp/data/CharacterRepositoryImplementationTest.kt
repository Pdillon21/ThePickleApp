package com.example.thepickleapp.data

import com.example.thepickleapp.data.remote.api.RickAndMortyApi
import com.example.thepickleapp.data.repo.CharacterRepositoryImplementation
import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever
import retrofit2.Response


class CharacterRepositoryImplementationTest {
    private companion object {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = Gson().toJson("Any").toResponseBody(mediaType)
    }

    private lateinit var api: RickAndMortyApi

    @Before
    fun setup() {
        api = Mockito.mock(RickAndMortyApi::class.java)
    }

    @Test
    fun `when api responds with error, repository implementation returns with status error`() {
        runBlocking {
            whenever(
                api.getCharacters(
                    null,
                    null,
                    null,
                    null,
                    null,
                    1
                )
            ).thenAnswer {
                Response.error<String>(400, body)
            }
        }

        val cri = CharacterRepositoryImplementation(api)
        assertEquals(
            runBlocking {
                cri.getCharacters(
                    null,
                    null,
                    null,
                    null,
                    null,
                    1
                ).status
            },
            PickleResponseStatus.SERVER_ERROR
        )
    }

    @Test
    fun `when api responds with success, repository implementation returns with status error`() {
        runBlocking {
            whenever(
                api.getCharacters(
                    null,
                    null,
                    null,
                    null,
                    null,
                    1
                )
            ).thenAnswer {
                Response.success(body)
            }
        }

        val cri = CharacterRepositoryImplementation(api)
        assertEquals(
            runBlocking {
                cri.getCharacters(
                    null,
                    null,
                    null,
                    null,
                    null,
                    1
                ).status
            },
            PickleResponseStatus.SUCCESS
        )
    }
}