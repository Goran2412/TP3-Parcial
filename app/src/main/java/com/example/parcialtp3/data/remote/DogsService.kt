package com.example.parcialtp3.data.remote

import com.example.parcialtp3.data.response.DogBreedsResponse
import javax.inject.Inject

class DogsService @Inject constructor(private val dogsApiClient: DogsApiClient) {
    suspend fun getBreeds(): DogBreedsResponse {
        return dogsApiClient.getBreeds()
    }
}