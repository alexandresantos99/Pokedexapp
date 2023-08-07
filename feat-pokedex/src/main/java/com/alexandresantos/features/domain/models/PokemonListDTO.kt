package com.alexandresantos.features.domain.models

import com.alexandresantos.features.data.remote.model.pokemonlist.PokemonListResponse

data class PokemonListDTO(
    val count: Int,
    val next: String? = "",
    val pokemonList: List<PokemonListItemDTO>
)


fun PokemonListResponse.mapToModel() = PokemonListDTO(
    count = count,
    next = next,
    pokemonList = pokemonListItems.map { it.mapToModel() }
)

