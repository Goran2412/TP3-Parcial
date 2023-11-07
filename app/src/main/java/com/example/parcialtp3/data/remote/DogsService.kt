package com.example.parcialtp3.data.remote

import com.example.parcialtp3.data.response.DogBreedsResponse
import com.example.parcialtp3.data.response.DogImagesResponse
import javax.inject.Inject

class DogsService @Inject constructor(private val dogsApiClient: DogsApiClient) {
    suspend fun getBreeds(): DogBreedsResponse {
        return dogsApiClient.getBreeds()
    }

    suspend fun getRandomImages(breed: String, count: Int): DogImagesResponse {
        return dogsApiClient.getRandomImages(breed, count)
    }

}