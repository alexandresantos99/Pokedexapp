package com.alexandresantos.features.presenter.models

import com.alexandresantos.features.domain.models.PokemonListDTO

data class PokemonListPresentation(
    val count: Int,
    val next: String? = "",
    val pokemonList: List<PokemonListItemPresentation>
)

fun PokemonListDTO.mapToPresentation() =
    PokemonListPresentation(
        count = count,
        next = next,
        pokemonList.map { it.mapToPresentation() }
    )

