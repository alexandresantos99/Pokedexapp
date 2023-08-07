package com.alexandresantos.features.domain.models

import com.alexandresantos.features.data.remote.model.pokemondetail.StatResponse

data class StatDTO(
    val baseStat: Int,
    val stat: StatXDTO
)

fun StatResponse.mapToModel() = StatDTO(
    baseStat = baseStat,
    stat = stat.mapToModel()
)
