package com.example.parcialtp3.domain.usecase

import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.model.DogModel
import com.example.parcialtp3.domain.repository.DogsRepository
import javax.inject.Inject

class AddDogToAdoptionListUseCase @Inject constructor(private val dogsRepository: DogsRepository) {
    suspend operator fun invoke(dog: DogModel): Result<Unit> {
        return dogsRepository.addDog(dog)
    }
}