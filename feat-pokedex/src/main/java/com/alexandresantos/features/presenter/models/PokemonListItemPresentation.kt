package com.alexandresantos.features.presenter.models

import com.alexandresantos.features.domain.models.PokemonListItemDTO
import java.util.Locale

data class PokemonListItemPresentation(
    val name: String,
    val url: String,
    val number: Int = 0
) {

    fun formatPokemonName(): String {
        return name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
        }
    }

    fun extractPokemonNumberFromUrl(): Int {
        val number = if (url.endsWith("/")) {
            url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            url.takeLastWhile { it.isDigit() }
        }
        return number.toInt()
    }
}

fun PokemonListItemDTO.mapToPresentation() = PokemonListItemPresentation(
    name = name,
    url = url,
    number = number
)


