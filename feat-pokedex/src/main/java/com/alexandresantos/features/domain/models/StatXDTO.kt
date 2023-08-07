package com.alexandresantos.features.domain.models

import com.alexandresantos.features.data.remote.model.pokemondetail.StatXResponse

data class StatXDTO(
    val name: String,
)
    fun StatXResponse.mapToModel() = StatXDTO(name = name)
