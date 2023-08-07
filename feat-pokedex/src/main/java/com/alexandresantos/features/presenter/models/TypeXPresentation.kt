package com.alexandresantos.features.presenter.models

import com.alexandresantos.features.domain.models.TypeXDTO

data class TypeXPresentation(
    val name: String
)

fun TypeXDTO.mapToPresentation() = TypeXPresentation(name = name)