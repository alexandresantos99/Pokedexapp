package com.alexandresantos.features.di

import com.alexandresantos.features.data.remote.repositories.PokemonRepository
import com.alexandresantos.features.domain.usecases.PokemonDetailUseCase
import com.alexandresantos.features.domain.usecases.PokemonDetailUseCaseImpl
import com.alexandresantos.features.domain.usecases.PokemonListUseCase
import com.alexandresantos.features.domain.usecases.PokemonListUseCaseImpl
import org.koin.dsl.module

val pokemonDomain = module {
    single {
        providesPokemonListUseCase(repository = get())
    }
    single {
        providesPokemonDetailUseCase(repository = get())
    }
}

fun providesPokemonListUseCase(repository: PokemonRepository) : PokemonListUseCase {
    return PokemonListUseCaseImpl(repository = repository)
}

fun providesPokemonDetailUseCase(repository: PokemonRepository) : PokemonDetailUseCase {
    return PokemonDetailUseCaseImpl(repository = repository)
}