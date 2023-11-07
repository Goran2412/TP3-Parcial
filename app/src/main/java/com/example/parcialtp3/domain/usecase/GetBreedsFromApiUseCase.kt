package com.example.parcialtp3.domain.usecase

import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.remote.DogsService
import com.example.parcialtp3.data.response.DogBreedsResponse
import com.example.parcialtp3.domain.repository.DogsRepository
import javax.inject.Inject

class GetBreedsFromApiUseCase @Inject constructor(private val dogsRepository: DogsRepository) {
    suspend operator fun invoke(): Result<DogBreedsResponse> {
        return dogsRepository.getAllBreeds()
    }
}