package com.alexandresantos.features.domain.models

import com.alexandresantos.features.data.remote.model.pokemondetail.PokemonResponse

data class PokemonDTO(
    val height: Int,
    val id: Int,
    val name: String,
    val stat: List<StatDTO>,
    val type: List<TypeDTO>,
    val weight: Int
)

fun PokemonResponse.mapToModel() = PokemonDTO(
    height = height,
    id = id,
    name = name,
    stat = stat.map { it.mapToModel() },
    type = type.map { it.mapToModel() },
    weight = weight
)








