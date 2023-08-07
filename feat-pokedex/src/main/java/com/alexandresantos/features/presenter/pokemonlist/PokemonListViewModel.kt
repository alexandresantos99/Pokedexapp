package com.alexandresantos.features.presenter.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandresantos.features.domain.usecases.PokemonListUseCase
import com.alexandresantos.features.domain.usecases.PokemonListUseCaseParams
import com.alexandresantos.features.presenter.models.PokemonListItemPresentation
import com.alexandresantos.features.utils.Constants.PAGE_SIZE
import com.alexandresantos.features.utils.Constants.errorMessageGeneric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(private val pokemonListUseCase: PokemonListUseCase) :
    ViewModel() {

    private val _listState = MutableStateFlow(
        PokemonListState(
            loading = true,
            error = "",
            list = emptyList()
        )
    )

    val listState: StateFlow<PokemonListState> = _listState

    private var cacheList: List<PokemonListItemPresentation> = emptyList()
    private var isSearchStarting = true


    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonListUseCase.execute(
                params = PokemonListUseCaseParams(
                    limit = PAGE_SIZE,
                    offset = _listState.value.currentPage * PAGE_SIZE
                )
            ).onCompletion {
                _listState.value = _listState.value.copy(loading = false)
            }.collect { result ->
                result.fold(onFailure = ::onFail, onSuccess = ::onSuccess)
            }
        }
    }

    fun searchPokemon(pokemon: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val listSearch = if (isSearchStarting) {
                _listState.value.list
            } else {
                cacheList
            }

            if (isSearchStarting) {
                cacheList = _listState.value.list
                isSearchStarting = false
            }

            if (pokemon.isBlank()) {
                _listState.update { it.copy(list = cacheList, isSearching = false) }
                isSearchStarting = true
                return@launch
            }

            val filteredList = filterPokemon(listSearch, pokemon)

            _listState.update { _listState.value.copy(list = filteredList, isSearching = true) }
        }
    }

    private fun filterPokemon(
        pokemonList: List<PokemonListItemPresentation>,
        query: String
    ): List<PokemonListItemPresentation> {
        return pokemonList.filter {
            it.name.contains(
                query.trim(),
                ignoreCase = true
            ) || it.number.toString() == query.trim()
        }
    }

    private fun onFail(error: Throwable) {
        _listState.value = _listState.value.copy(error = error.message ?: errorMessageGeneric)
    }

    private fun onSuccess(pokemonList: List<PokemonListItemPresentation>) {
        _listState.update {
            _listState.value.copy(
                currentPage = _listState.value.currentPage + 1,
                loading = false,
                list = _listState.value.list + pokemonList
            )
        }
    }
}