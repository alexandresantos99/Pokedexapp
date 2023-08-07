package com.alexandresantos.features.domain.usecases

import com.alexandresantos.features.data.remote.repositories.PokemonRepository
import com.alexandresantos.features.domain.models.PokemonDTO
import kotlinx.coroutines.flow.Flow

interface PokemonDetailUseCase :
    UseCase<PokemonDTO, PokemonDetailUseCaseParams> {
    override suspend fun execute(params: PokemonDetailUseCaseParams): Flow<Result<PokemonDTO>>
}

class PokemonDetailUseCaseImpl(private val repository: PokemonRepository) :
    PokemonDetailUseCase {

    override suspend fun execute(params: PokemonDetailUseCaseParams) =
        repository.getPokemonDetail(pokemonName = params.pokemonName)

}

data class PokemonDetailUseCaseParams(
    val pokemonName: String = ""
) : UseCaseParam()