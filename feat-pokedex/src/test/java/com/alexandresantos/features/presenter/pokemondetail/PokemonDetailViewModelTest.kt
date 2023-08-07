package com.alexandresantos.features.presenter.pokemondetail

import com.alexandresantos.features.domain.models.mapToModel
import com.alexandresantos.features.domain.usecases.PokemonDetailUseCase
import com.alexandresantos.features.mocks.pokemonResponseMockk
import com.alexandresantos.features.presenter.models.mapToPresentation
import com.alexandresantos.features.utils.Constants
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PokemonDetailViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given success when calls getPokemonDetail should return detail pokemon`() = runBlockingTest {
            val useCase: PokemonDetailUseCase = mockk()
            coEvery { useCase.execute(any()) } returns flow {
                emit(
                    Result.success(
                        pokemonResponseMockk.mapToModel()
                    )
                )
            }
            val viewModel = PokemonDetailViewModel(useCase)
            viewModel.getPokemonDetail("")

            Assert.assertEquals(
                pokemonResponseMockk.mapToModel().mapToPresentation(),
                viewModel.pokemonDetailState.value.pokemonDetail
            )
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given error when calls getPokemonDetail should return detail pokemon`() = runBlockingTest {
        val useCase: PokemonDetailUseCase = mockk()
        coEvery { useCase.execute(any()) } returns flow { emit(Result.failure(RuntimeException())) }

        val viewModel = PokemonDetailViewModel(useCase)
        viewModel.getPokemonDetail("")

        Assert.assertEquals(
            Constants.errorMessageGeneric,
            viewModel.pokemonDetailState.value.error
        )
    }
}