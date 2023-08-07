package com.alexandresantos.features.presenter.pokemondetail

import com.alexandresantos.features.presenter.models.PokemonPresentation

data class PokemonDetailState(
    val loading: Boolean = true,
    val error: String = "",
    val pokemonDetail: PokemonPresentation
)
