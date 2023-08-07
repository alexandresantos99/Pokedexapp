package com.alexandresantos.features.di

import com.alexandresantos.features.data.remote.repositories.PokemonRepositoryImpl
import com.alexandresantos.features.data.remote.repositories.PokemonRepository
import com.alexandresantos.features.data.remote.services.PokemonService
import org.koin.dsl.module

val pokemonRepository = module {
    single {
        providesRepositoryImpl(service = get())
    }
}

fun providesRepositoryImpl(service: PokemonService) : PokemonRepository {
    return PokemonRepositoryImpl(service)
}