package com.alexandresantos.features.domain

import com.alexandresantos.features.data.remote.repositories.PokemonRepository
import com.alexandresantos.features.domain.models.mapToModel
import com.alexandresantos.features.domain.usecases.PokemonListUseCaseImpl
import com.alexandresantos.features.domain.usecases.PokemonListUseCaseParams
import com.alexandresantos.features.mocks.pokemonListResponseMockk
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonListUseCaseImplTest {

    private lateinit var useCase: PokemonListUseCaseImpl
    private var repository: PokemonRepository = mockk()

    @Before
    fun setUp() {
        clearAllMocks()
        useCase = PokemonListUseCaseImpl(repository)
    }

    @Test
    fun `given getPokemonList success then should returns object`() = runTest {
        val params = PokemonListUseCaseParams(limit = 20, offset = 0)
        val mockResult = Result.success(pokemonListResponseMockk.mapToModel())
        val mockedResult = useCase.pokemonListMapper(mockResult)


        coEvery {
            repository.getPokemonList(
                offset = params.offset,
                limit = params.limit
            )
        } returns flowOf(mockResult)

        val resultFlow = useCase.execute(params)

        Assert.assertEquals(mockedResult, resultFlow.single())
    }

    @Test
    fun `given getPokemonList returns error then should returns exception`() = runTest {
        val params = PokemonListUseCaseParams(limit = 20, offset = 0)

        coEvery {
            repository.getPokemonList(any(), any())
        } returns flowOf(Result.failure(RuntimeException()))

        val resultFlow = useCase.execute(params)
        val result = resultFlow.single()

        Assert.assertTrue(result.exceptionOrNull() is RuntimeException)
    }
}