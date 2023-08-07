package com.alexandresantos.features.domain.models

import com.alexandresantos.features.data.remote.model.pokemondetail.TypeResponse

data class TypeDTO(
    val type: TypeXDTO
)
    fun TypeResponse.mapToModel() = TypeDTO(type = type.mapToModel())

