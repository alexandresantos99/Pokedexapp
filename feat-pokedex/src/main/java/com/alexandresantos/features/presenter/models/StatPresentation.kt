package com.alexandresantos.features.presenter.models

import com.alexandresantos.features.domain.models.StatDTO

data class StatPresentation(
    val baseStat: Int,
    val stat: StatXPresentation
)

fun StatDTO.mapToPresentation() = StatPresentation(
    baseStat = baseStat,
    stat = stat.mapToPresentation()
)