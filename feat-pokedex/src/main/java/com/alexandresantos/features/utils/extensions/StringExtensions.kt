package com.alexandresantos.features.utils.extensions

internal fun String.toPokemonImageUrl(pokemonId: Int) =
    "$this${String.format("%03d", pokemonId)}.png"