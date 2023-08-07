package com.alexandresantos.features.data.remote.repositories

import com.alexandresantos.features.data.remote.services.PokemonService
import com.alexandresantos.features.domain.models.PokemonDTO
import com.alexandresantos.features.domain.models.mapToModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(private val service: PokemonService) : PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int) = flow {
        try {
            val response = service.getPokemonList(limit, offset)
            emit(Result.success(response.mapToModel()))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getPokemonDetail(pokemonName: String): Flow<Result<PokemonDTO>> = flow {
        try {
            val response = service.getPokemonDetail(pokemonName)
            emit(Result.success(response.mapToModel()))
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}