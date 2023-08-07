package com.alexandresantos.features.domain.models

import com.alexandresantos.features.data.remote.model.pokemonlist.PokemonListItemResponse

data class PokemonListItemDTO(
    val name: String,
    val url: String,
    val number: Int = 0
)

fun PokemonListItemResponse.mapToModel() = PokemonListItemDTO(
    name = name,
    url = url,
)


