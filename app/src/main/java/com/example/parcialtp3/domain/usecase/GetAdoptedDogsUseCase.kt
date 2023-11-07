package com.example.parcialtp3.domain.usecase

import com.example.parcialtp3.common.Result
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.domain.repository.DogsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAdoptedDogsUseCase @Inject constructor(private val dogsRepository: DogsRepository) {
    operator fun invoke(): Flow<Result<List<Dog>>> {
        return dogsRepository.getAdoptedDogs()
    }
}