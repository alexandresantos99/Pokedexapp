package com.alexandresantos.features.mocks

import com.alexandresantos.features.data.remote.model.pokemondetail.PokemonResponse
import com.alexandresantos.features.data.remote.model.pokemonlist.PokemonListItemResponse
import com.alexandresantos.features.data.remote.model.pokemonlist.PokemonListResponse
import com.alexandresantos.features.presenter.models.PokemonListItemPresentation

val pokemonListResponseMockk = PokemonListResponse(
    count = 0,
    next = "a",
    pokemonListItems = listOf(
        PokemonListItemResponse(
            "bulbasaur",
            "https://pokeapi.co/api/v2/pokemon/1/"
        )
    )
)

val listPresentationMockk = listOf(
    PokemonListItemPresentation(
        "bulbasaur",
        "https://pokeapi.co/api/v2/pokemon/1/"
    ),
    PokemonListItemPresentation(
        "pikachu",
        "https://pokeapi.co/api/v2/pokemon/1/"
    )
)

val pokemonResponseMockk = PokemonResponse(
    height = 0,
    id = 0,
    name = "",
    stat = emptyList(),
    type = emptyList(),
    weight = 0
)