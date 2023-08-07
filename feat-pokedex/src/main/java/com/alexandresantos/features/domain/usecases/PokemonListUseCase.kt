package com.alexandresantos.features.domain.usecases

import com.alexandresantos.features.data.remote.repositories.PokemonRepository
import com.alexandresantos.features.domain.models.PokemonListDTO
import com.alexandresantos.features.domain.models.PokemonListItemDTO
import com.alexandresantos.features.presenter.models.PokemonListItemPresentation
import com.alexandresantos.features.presenter.models.mapToPresentation
import com.alexandresantos.features.utils.Constants
import com.alexandresantos.features.utils.extensions.toPokemonImageUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface PokemonListUseCase :
    UseCase<List<PokemonListItemPresentation>, PokemonListUseCaseParams> {
    override suspend fun execute(params: PokemonListUseCaseParams): Flow<Result<List<PokemonListItemPresentation>>>
}

class PokemonListUseCaseImpl(private val repository: PokemonRepository) :
    PokemonListUseCase {

    override suspend fun execute(params: PokemonListUseCaseParams): Flow<Result<List<PokemonListItemPresentation>>> =
        flow {
            repository.getPokemonList(limit = params.limit, offset = params.offset)
                .map { pokemonListMapper(result = it) }.collect { emit(it) }
        }

    internal fun pokemonListMapper(result: Result<PokemonListDTO>): Result<List<PokemonListItemPresentation>> {
        return result.map { pokemonListDTO ->
            val pokedexEntries = pokemonListDTO.mapToPresentation().pokemonList.flatMap { entry ->
                listOf(
                    PokemonListItemDTO(
                        name = entry.formatPokemonName(),
                        url = Constants.pokemonImageUrl.toPokemonImageUrl(entry.extractPokemonNumberFromUrl()),
                        number = entry.extractPokemonNumberFromUrl()
                    ).mapToPresentation()
                )
            }
            pokedexEntries
        }
    }
}

data class PokemonListUseCaseParams(
    val limit: Int = 20,
    val offset: Int = 1
) : UseCaseParam()