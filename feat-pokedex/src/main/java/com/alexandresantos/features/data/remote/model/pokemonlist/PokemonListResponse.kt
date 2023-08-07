package com.alexandresantos.features.data.remote.model.pokemonlist

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String? = "",
    @SerializedName("results")
    val pokemonListItems: List<PokemonListItemResponse>,
)

data class PokemonListItemResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)