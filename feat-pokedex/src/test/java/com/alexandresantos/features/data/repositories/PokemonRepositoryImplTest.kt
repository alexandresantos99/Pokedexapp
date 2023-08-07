package com.alexandresantos.features.data.repositories

import com.alexandresantos.features.data.remote.repositories.PokemonRepository
import com.alexandresantos.features.domain.models.mapToModel
import com.alexandresantos.features.mocks.pokemonListResponseMockk
import com.alexandresantos.features.mocks.pokemonResponseMockk
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any

class PokemonRepositoryImplTest {

    private var repository: PokemonRepository = mockk()

    @Before
    fun before(){
        clearAllMocks()
    }

    @Test
    fun `given getPokemonList returns success then should returns object`() = runTest {

        coEvery {
            repository.getPokemonList(mockk(), mockk())
        } returns flowOf(Result.success(pokemonListResponseMockk.mapToModel()))

        val resultFlow = repository.getPokemonList(any(), any())

        val result = resultFlow.single()

        assertEquals(Result.success(pokemonListResponseMockk.mapToModel()), result)
    }

    @Test
    fun `given getPokemonList returns error then should returns exception`() = runTest {
        coEvery {
            repository.getPokemonList(any(), any())
        } returns flowOf(Result.failure(RuntimeException()))

        val resultFlow = repository.getPokemonList(any(), any())

        val result = resultFlow.single()

        assertTrue(result.exceptionOrNull() is Exception)
    }

    @Test
    fun `given getPokemonDetail returns success then should returns object`() = runTest {


        coEvery {
            repository.getPokemonDetail("")
        } returns flowOf(Result.success(pokemonResponseMockk.mapToModel()))

        val resultFlow = repository.getPokemonDetail("")

        val result = resultFlow.single()

        assertEquals(Result.success(pokemonResponseMockk.mapToModel()), result)
    }

    @Test
    fun `given getPokemonDetail returns error then should returns exception`() = runTest {
        coEvery {
            repository.getPokemonDetail(any())
        } returns flowOf(Result.failure(RuntimeException()))

        val resultFlow = repository.getPokemonDetail(any())

        val result = resultFlow.single()

        assertTrue(result.exceptionOrNull() is Exception)
    }
}