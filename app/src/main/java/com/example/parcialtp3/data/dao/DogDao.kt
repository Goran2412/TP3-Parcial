package com.example.parcialtp3.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parcialtp3.data.model.DogModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {

    @Insert
    fun insert(dogModel: DogModel)

    @Query("SELECT * FROM dogs")
    // suspend fun getAllDogs(): List<DogModel>
    fun getAllDogs(): Flow<List<DogModel>> //this should be Flow<List<DogModel>>

}