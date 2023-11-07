package com.example.parcialtp3.data.repository

import android.util.Log
import com.example.parcialtp3.data.dao.DogDao
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.domain.repository.DogsRepository
import kotlinx.coroutines.flow.Flow
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.model.DogModel
import com.example.parcialtp3.data.remote.DogsService
import com.example.parcialtp3.data.response.DogBreedsResponse
import com.example.parcialtp3.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


private const val TAG = "DogsRepositoryImpl"

class DogsRepositoryImpl @Inject constructor(
    private val dogDao: DogDao,
    private val dogsService: DogsService
) : DogsRepository {
    init {
        Log.d(TAG, "init")
    }

    override fun getAllDogs(): Flow<Result<List<Dog>>> {
        return dogDao.getAllDogs()
            .map { dogModels ->
                val dogs = dogModels.map { it.toDomain() }
                Result.Success(dogs) as Result<List<Dog>>
            }
            .onStart { emit(Result.Loading) }
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(Result.Error(e.message ?: "Error in dogs database"))
            }
    }

    override fun getFavoriteDogs(): Flow<Result<List<Dog>>> {
        return dogDao.getFavoriteDogs()
            .map { dogModels ->
                val favoriteDogs = dogModels.filter { it.isFavourite }.map { it.toDomain() }
                Result.Success(favoriteDogs) as Result<List<Dog>>
            }
            .onStart { emit(Result.Loading) }
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(Result.Error(e.message ?: "Error in fetching favorite dogs"))
            }
    }

    override fun getAdoptedDogs(): Flow<Result<List<Dog>>> {
        return dogDao.getAdoptedDogs()
            .map { dogModels ->
                val adoptedDogs = dogModels.filter { it.isAdopted }.map { it.toDomain() }
                Result.Success(adoptedDogs) as Result<List<Dog>>
            }
            .onStart { emit(Result.Loading) }
            .flowOn(Dispatchers.IO)
            .catch { e ->
                emit(Result.Error(e.message ?: "Error in fetching adopted dogs"))
            }
    }


    override suspend fun getAllBreeds(): Result<DogBreedsResponse> {
        return try {
            val response = dogsService.getBreeds()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun addDog(dog: DogModel): Result<Unit> {
        return try {
            dogDao.insert(dog)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}

//    override fun getAllDogs(): Flow<List<Dog>> {
//        return dogDao.getAllDogs().map { dogModels ->
//            dogModels.map { dogModel ->
//                Dog(
//                    id = dogModel.id,
//                    name = dogModel.name,
//                    breed = dogModel.breed
//                )
//            }
//        }
//    }

//    override suspend fun getAllDogs(): Result<List<Dog>> {
//        return try {
//            val dogModels = dogDao.getAllDogs().toList() // Collect the flow into a list
//            // If the query was successful, map the data to domain objects and return a Success result
//            val dogs = dogModels.flatMap { it } // Flatten the list of lists
//            Result.Success(dogs.map { it.toDomain() })
//        } catch (e: Exception) {
//            // If an error occurred, return an Error result with the error message
//            Result.Error(e.message ?: "An error occurred")
//        }
//    }override fun getAllDogs(): Flow<List<Dog>> {
//        return dogDao.getAllDogs().map { dogModels ->
//            dogModels.map { dogModel ->
//                Dog(
//                    id = dogModel.id,
//                    name = dogModel.name,
//                    breed = dogModel.breed
//                )
//            }
//        }
//    }


