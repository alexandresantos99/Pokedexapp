package com.alexandresantos.features.router

sealed class Destination(val route: String) {

    object PokemonList : Destination("pokemon_list")

    object PokemonDetail : Destination("pokemon_detail/{pokemonName}/{dominantColor}") {
        fun buildRoute(pokemonName: String, dominantColor: Int) =
            "pokemon_detail/${pokemonName}/${dominantColor}"
    }
}


