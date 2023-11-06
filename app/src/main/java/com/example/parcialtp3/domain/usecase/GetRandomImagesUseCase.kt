package com.example.parcialtp3.domain.usecase

import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.response.DogImagesResponse
import com.example.parcialtp3.domain.repository.DogsRepository
import javax.inject.Inject

class GetRandomImagesUseCase @Inject constructor(private val dogsRepository: DogsRepository) {
    suspend operator fun invoke(breed: String, count: Int): Result<DogImagesResponse> {
        return dogsRepository.getRandomImages(breed, count)
    }
}