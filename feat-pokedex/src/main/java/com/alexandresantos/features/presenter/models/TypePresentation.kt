package com.alexandresantos.features.presenter.models

import com.alexandresantos.features.domain.models.TypeDTO

data class TypePresentation(
    val type: TypeXPresentation
)

fun TypeDTO.mapToPresentation() = TypePresentation(type = type.mapToPresentation())