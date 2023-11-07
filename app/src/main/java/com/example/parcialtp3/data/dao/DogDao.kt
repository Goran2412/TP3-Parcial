package com.example.parcialtp3.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.parcialtp3.data.model.DogModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.PUT

@Dao
interface DogDao {

    @Insert
    fun insert(dogModel: DogModel)

    @Query("SELECT * FROM dogs WHERE id = :dogId")
    suspend fun getDogById(dogId: Int): DogModel?

    @Update
    suspend fun updateDog(dogModel: DogModel)

    @Query("SELECT * FROM dogs")
    // suspend fun getAllDogs(): List<DogModel>
    fun getAllDogs(): Flow<List<DogModel>> //this should be Flow<List<DogModel>>

    @Query("SELECT * FROM dogs WHERE isFavourite = 1")
    fun getFavoriteDogs(): Flow<List<DogModel>>

}