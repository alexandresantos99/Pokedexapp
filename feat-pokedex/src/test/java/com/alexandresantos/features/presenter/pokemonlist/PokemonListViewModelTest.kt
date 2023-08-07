package com.alexandresantos.features.presenter.pokemonlist

import com.alexandresantos.features.domain.usecases.PokemonListUseCase
import com.alexandresantos.features.mocks.listPresentationMockk
import com.alexandresantos.features.utils.Constants
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PokemonListViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        clearAllMocks()
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given success when calls getPokemonList should return list of pokemon`() =
        runBlockingTest {
            val useCase: PokemonListUseCase = mockk()
            coEvery { useCase.execute(any()) } returns flowOf(Result.success(listPresentationMockk))
            val viewModel = PokemonListViewModel(useCase)
            Assert.assertEquals(listPresentationMockk, viewModel.listState.value.list)

        }

    @Test
    fun `given error when calls getPokemonList should return exception`() {
        val useCase: PokemonListUseCase = mockk()
        coEvery { useCase.execute(any()) } returns flowOf(Result.failure(RuntimeException()))
        val viewModel = PokemonListViewModel(useCase)

        Assert.assertEquals(Constants.errorMessageGeneric, viewModel.listState.value.error)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given empty pokemon name when search pokemon then should return initial list`() =
        runBlockingTest {
            val useCase: PokemonListUseCase = mockk()
            coEvery { useCase.execute(any()) } returns flowOf(Result.success(listPresentationMockk))
            val viewModel = PokemonListViewModel(useCase)

            viewModel.searchPokemon("")
            Assert.assertEquals(2, viewModel.listState.value.list.size)

        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given not empty pokemon name when search pokemon then should return filtered list`() =
        runBlockingTest {
            val useCase: PokemonListUseCase = mockk()
            coEvery { useCase.execute(any()) } returns flowOf(Result.success(listPresentationMockk))
            val viewModel = PokemonListViewModel(useCase)

            viewModel.searchPokemon("pikachu")
            delay(8000)
            Assert.assertEquals(1, viewModel.listState.value.list.size)

        }
}