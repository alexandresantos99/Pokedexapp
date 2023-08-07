package com.alexandresantos.pokedexbyalexandre

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.alexandresantos.features.Pokedex

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, Pokedex::class.java))
    }
}