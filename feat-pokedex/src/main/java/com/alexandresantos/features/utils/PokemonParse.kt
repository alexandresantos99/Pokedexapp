package com.alexandresantos.features.utils

import androidx.compose.ui.graphics.Color
import com.alexandresantos.features.presenter.models.StatPresentation
import com.alexandresantos.features.presenter.models.TypePresentation
import com.alexandresantos.features.ui.theme.AtkColor
import com.alexandresantos.features.ui.theme.DefColor
import com.alexandresantos.features.ui.theme.HPColor
import com.alexandresantos.features.ui.theme.SpAtkColor
import com.alexandresantos.features.ui.theme.SpDefColor
import com.alexandresantos.features.ui.theme.SpdColor
import com.alexandresantos.features.ui.theme.TypeBug
import com.alexandresantos.features.ui.theme.TypeDark
import com.alexandresantos.features.ui.theme.TypeDragon
import com.alexandresantos.features.ui.theme.TypeElectric
import com.alexandresantos.features.ui.theme.TypeFairy
import com.alexandresantos.features.ui.theme.TypeFighting
import com.alexandresantos.features.ui.theme.TypeFire
import com.alexandresantos.features.ui.theme.TypeFlying
import com.alexandresantos.features.ui.theme.TypeGhost
import com.alexandresantos.features.ui.theme.TypeGrass
import com.alexandresantos.features.ui.theme.TypeGround
import com.alexandresantos.features.ui.theme.TypeIce
import com.alexandresantos.features.ui.theme.TypeNormal
import com.alexandresantos.features.ui.theme.TypePoison
import com.alexandresantos.features.ui.theme.TypePsychic
import com.alexandresantos.features.ui.theme.TypeRock
import com.alexandresantos.features.ui.theme.TypeSteel
import com.alexandresantos.features.ui.theme.TypeWater
import java.util.Locale

fun parseTypeToColor(typeResponse: TypePresentation): Color {
    return when (typeResponse.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(statResponse: StatPresentation): Color {
    return when (statResponse.stat.name.lowercase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(statResponse: StatPresentation): String {
    return when (statResponse.stat.name.lowercase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "S-Atk "
        "special-defense" -> "S-Def "
        "speed" -> "Speed"
        else -> ""
    }
}