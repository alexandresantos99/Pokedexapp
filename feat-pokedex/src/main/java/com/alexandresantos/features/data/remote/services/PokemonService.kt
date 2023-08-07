package com.alexandresantos.features.data.remote.services

import com.alexandresantos.features.data.remote.model.pokemondetail.PokemonResponse
import com.alexandresantos.features.data.remote.model.pokemonlist.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetail(
        @Path("pokemonName") pokemonName: String
    ) : PokemonResponse
}