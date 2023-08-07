package com.alexandresantos.features.presenter.pokemonlist

import com.alexandresantos.features.presenter.models.PokemonListItemPresentation

data class PokemonListState(
    val loading: Boolean = true,
    val error: String = "",
    val isSearching: Boolean = false,
    val currentPage: Int = 0,
    val list: List<PokemonListItemPresentation> = emptyList(),
)