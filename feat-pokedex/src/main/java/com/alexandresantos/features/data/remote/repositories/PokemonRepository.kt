package com.alexandresantos.features.data.remote.repositories

import com.alexandresantos.features.domain.models.PokemonDTO
import com.alexandresantos.features.domain.models.PokemonListDTO
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Flow<Result<PokemonListDTO>>

    suspend fun getPokemonDetail(pokemonName: String): Flow<Result<PokemonDTO>>
}