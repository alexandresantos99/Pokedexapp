package com.alexandresantos.features.presenter

import android.app.Application
import com.alexandresantos.features.di.apiModule
import com.alexandresantos.features.di.pokemonDomain
import com.alexandresantos.features.di.pokemonRepository
import com.alexandresantos.features.di.viewModelsModule
import org.koin.core.context.startKoin

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(apiModule, pokemonRepository, pokemonDomain, viewModelsModule)
        }
    }
}