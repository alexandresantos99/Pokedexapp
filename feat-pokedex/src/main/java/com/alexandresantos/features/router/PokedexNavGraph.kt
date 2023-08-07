package com.alexandresantos.features.router

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexandresantos.features.presenter.pokemondetail.PokemonDetailPresenter
import com.alexandresantos.features.presenter.pokemonlist.PokemonListPresenter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavigationAppHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destination.PokemonList.route) {
        composable(Destination.PokemonList.route) {
            PokemonListPresenter(navController = navController)
        }

        composable(Destination.PokemonDetail.route) {
            val pokemonName = remember { it.arguments?.getString("pokemonName") }
            val dominantColor = remember {
                val color = it.arguments?.getString("dominantColor")
                color?.let { Color(it.toInt()) } ?: Color.White
            }
            PokemonDetailPresenter(
                navController = navController,
                pokemonName = pokemonName?.lowercase() ?: "",
                dominantColor = dominantColor
            )
        }
    }
}