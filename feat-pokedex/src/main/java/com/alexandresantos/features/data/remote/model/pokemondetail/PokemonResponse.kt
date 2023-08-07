package com.alexandresantos.features.data.remote.model.pokemondetail

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class PokemonResponse(
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("stats")
    val stat: List<StatResponse>,
    @SerializedName("types")
    val type: List<TypeResponse>,
    @SerializedName("weight")
    val weight: Int
)

data class StatResponse(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerialName("stat")
    val stat: StatXResponse
)

data class StatXResponse(
    @SerialName("name")
    val name: String,
)

data class TypeResponse(
    @SerialName("type")
    val type: TypeXResponse
)

data class TypeXResponse(
    @SerialName("name")
    val name: String
)