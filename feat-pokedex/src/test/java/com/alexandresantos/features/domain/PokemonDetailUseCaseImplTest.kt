package com.alexandresantos.features.domain

import com.alexandresantos.features.data.remote.repositories.PokemonRepository
import com.alexandresantos.features.domain.models.mapToModel
import com.alexandresantos.features.domain.usecases.PokemonDetailUseCaseImpl
import com.alexandresantos.features.domain.usecases.PokemonDetailUseCaseParams
import com.alexandresantos.features.mocks.pokemonResponseMockk
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PokemonDetailUseCaseImplTest {

    private lateinit var useCase: PokemonDetailUseCaseImpl
    private var repository: PokemonRepository = mockk()

    @Before
    fun setUp() {
        clearAllMocks()
        useCase = PokemonDetailUseCaseImpl(repository)
    }

    @Test
    fun `given getPokemonDetail returns success then should returns object`() = runTest {
        val params = PokemonDetailUseCaseParams(pokemonName = "")

        coEvery { repository.getPokemonDetail(params.pokemonName) } returns flowOf(
            Result.success(
                pokemonResponseMockk.mapToModel()
            )
        )

        val resultFlow = useCase.execute(params)
        Assert.assertEquals(Result.success(pokemonResponseMockk.mapToModel()), resultFlow.single())
    }

    @Test
    fun `given getPokemonDetail returns error then should returns exception`() = runTest {
        val params = PokemonDetailUseCaseParams(pokemonName = "")

        coEvery { repository.getPokemonDetail(params.pokemonName) } returns flowOf(
            Result.failure(
                RuntimeException()
            )
        )

        val resultFlow = useCase.execute(params)

        Assert.assertTrue(resultFlow.single().exceptionOrNull() is RuntimeException)

    }
}