package com.alexandresantos.features

import android.app.Application
import com.alexandresantos.features.di.apiModule
import com.alexandresantos.features.di.pokemonDomain
import com.alexandresantos.features.di.pokemonRepository
import com.alexandresantos.features.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexApplication)
            modules(apiModule, pokemonRepository, pokemonDomain, viewModelsModule)
        }
    }
}