package com.alexandresantos.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.alexandresantos.features.router.NavigationAppHost
import com.alexandresantos.features.ui.theme.PokedexByAlexandreTheme

class Pokedex : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexByAlexandreTheme {
                val navController = rememberNavController()
                NavigationAppHost(navController)
            }
        }
    }
}