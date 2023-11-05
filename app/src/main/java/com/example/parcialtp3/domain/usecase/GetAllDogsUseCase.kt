package com.example.parcialtp3.domain.usecase

import android.util.Log
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.domain.repository.DogsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


private const val TAG = "GetAllDogsUseCase"
class GetAllDogsUseCase @Inject constructor(private val dogsRepository: DogsRepository) {
//    suspend operator fun invoke(): Result<List<Dog>> {
//        Log.d(TAG, "invoke()")
//        return dogsRepository.getAllDogs()
//    }
operator fun invoke(): Flow<Result<List<Dog>>> {
    return dogsRepository.getAllDogs()
}

}