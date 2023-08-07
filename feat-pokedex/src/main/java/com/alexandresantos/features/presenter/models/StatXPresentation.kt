package com.alexandresantos.features.presenter.models

import com.alexandresantos.features.domain.models.StatXDTO

data class StatXPresentation(
    val name: String,
)
fun StatXDTO.mapToPresentation() = StatXPresentation(name = name)