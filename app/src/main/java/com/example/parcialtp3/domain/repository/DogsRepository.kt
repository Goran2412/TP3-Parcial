package com.example.parcialtp3.domain.repository

import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.model.DogModel
import com.example.parcialtp3.data.response.DogBreedsResponse
import com.example.parcialtp3.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    fun getAllDogs(): Flow<Result<List<Dog>>>
    suspend fun getAllBreeds(): Result<DogBreedsResponse>

    fun getFavoriteDogs(): Flow<Result<List<Dog>>>

    fun getAdoptedDogs(): Flow<Result<List<Dog>>>
    suspend fun addDog(dog: DogModel): Result<Unit>

}