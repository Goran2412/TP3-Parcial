package com.example.parcialtp3.domain.repository

import com.example.parcialtp3.common.Result
import com.example.parcialtp3.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
   // suspend fun getAllDogs(): Result<List<Dog>>
   //fun getAllDogs(): Flow<Result<List<Dog>>>


  // suspend fun getAllDogs(): Result<List<Dog>>

   fun getAllDogs(): Flow<Result<List<Dog>>>

}