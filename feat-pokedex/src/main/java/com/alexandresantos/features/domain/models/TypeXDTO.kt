package com.alexandresantos.features.domain.models

import com.alexandresantos.features.data.remote.model.pokemondetail.TypeXResponse

data class TypeXDTO(
    val name: String
)

fun TypeXResponse.mapToModel() = TypeXDTO(name = name)
