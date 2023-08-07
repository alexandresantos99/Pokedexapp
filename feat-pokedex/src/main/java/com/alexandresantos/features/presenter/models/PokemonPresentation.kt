package com.alexandresantos.features.presenter.models

import com.alexandresantos.features.domain.models.PokemonDTO

data class PokemonPresentation(
    val height: Int,
    val id: Int,
    val name: String,
    val stat: List<StatPresentation>,
    val type: List<TypePresentation>,
    val weight: Int
)

fun PokemonDTO.mapToPresentation() = PokemonPresentation(
    height = height,
    id = id,
    name = name,
    stat = stat.map { it.mapToPresentation() },
    type = type.map { it.mapToPresentation() },
    weight = weight
)