package com.alexandresantos.features.di

import com.alexandresantos.features.domain.usecases.PokemonDetailUseCase
import com.alexandresantos.features.domain.usecases.PokemonListUseCase
import com.alexandresantos.features.presenter.pokemondetail.PokemonDetailViewModel
import com.alexandresantos.features.presenter.pokemonlist.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        providesPokemonListViewModel(get())
    }

    viewModel {
        providesPokemonDetailViewModel(get())
    }
}

fun providesPokemonListViewModel(pokemonListUseCase: PokemonListUseCase): PokemonListViewModel {
    return PokemonListViewModel(pokemonListUseCase = pokemonListUseCase)
}

fun providesPokemonDetailViewModel(pokemonDetailUseCase: PokemonDetailUseCase): PokemonDetailViewModel {
    return PokemonDetailViewModel(pokemonDetailUseCase = pokemonDetailUseCase)
}