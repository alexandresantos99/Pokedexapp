package com.alexandresantos.features.presenter.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandresantos.features.domain.models.PokemonDTO
import com.alexandresantos.features.domain.usecases.PokemonDetailUseCase
import com.alexandresantos.features.domain.usecases.PokemonDetailUseCaseParams
import com.alexandresantos.features.presenter.models.PokemonPresentation
import com.alexandresantos.features.presenter.models.mapToPresentation
import com.alexandresantos.features.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val pokemonDetailUseCase: PokemonDetailUseCase) : ViewModel() {

    private val _pokemonDetail = MutableStateFlow(
        PokemonDetailState(
            pokemonDetail = PokemonPresentation(
                height = 0,
                id = 0,
                stat = emptyList(),
                type = emptyList(),
                name = "",
                weight = 0
            )
        )
    )

    val pokemonDetailState: StateFlow<PokemonDetailState> = _pokemonDetail

    init {
        _pokemonDetail.value = _pokemonDetail.value.copy(loading = true)
    }

    fun getPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
            pokemonDetailUseCase.execute(
                params = PokemonDetailUseCaseParams(
                    pokemonName = pokemonName
                )
            )
                .onCompletion {
                    _pokemonDetail.value = _pokemonDetail.value.copy(loading = false)
                }
                .collect { result ->
                    result.fold(onFailure = ::onFail, onSuccess = ::onSuccess)
                }
        }
    }

    private fun onFail(error: Throwable) {
        _pokemonDetail.update {
            _pokemonDetail.value.copy(
                error = error.message ?: Constants.errorMessageGeneric
            )
        }
    }

    private fun onSuccess(pokemonDetail: PokemonDTO) {
        _pokemonDetail.update {
            _pokemonDetail.value.copy(
                pokemonDetail = pokemonDetail.mapToPresentation()
            )
        }
    }
}