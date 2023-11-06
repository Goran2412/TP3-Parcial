package com.example.parcialtp3.domain.repository

import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.response.DogBreedsResponse
import com.example.parcialtp3.data.response.DogImagesResponse
import com.example.parcialtp3.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    // suspend fun getAllDogs(): Result<List<Dog>>
    //fun getAllDogs(): Flow<Result<List<Dog>>>


    // suspend fun getAllDogs(): Result<List<Dog>>

    fun getAllDogs(): Flow<Result<List<Dog>>>

    suspend fun getAllBreeds(): Result<DogBreedsResponse>

    suspend fun getRandomImages(breed: String, count: Int): Result<DogImagesResponse>

}