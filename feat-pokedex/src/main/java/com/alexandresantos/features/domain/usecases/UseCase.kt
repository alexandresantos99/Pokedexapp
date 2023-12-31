package com.alexandresantos.features.domain.usecases

import kotlinx.coroutines.flow.Flow

interface UseCase<T,P : UseCaseParam >{
    suspend fun execute(params: P): Flow<Result<T>>
}

abstract class UseCaseParam