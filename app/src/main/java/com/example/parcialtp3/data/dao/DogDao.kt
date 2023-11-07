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

    @Query("SELECT * FROM dogs WHERE isAdopted = 0")


    fun getAllDogs(): Flow<List<DogModel>>

    @Query("SELECT * FROM dogs WHERE isFavourite = 1")
    fun getFavoriteDogs(): Flow<List<DogModel>>

    @Query("SELECT * FROM dogs WHERE isAdopted = 1")
    fun getAdoptedDogs(): Flow<List<DogModel>>

    @Query("UPDATE dogs SET isAdopted = 1, isFavourite = 0 WHERE id = :dogId")
    suspend fun adoptDog(dogId: Int)

    @Query("SELECT DISTINCT breed FROM dogs")
    fun getDistinctBreeds(): List<String>

    @Query("SELECT DISTINCT subbreed FROM dogs")
    fun getDistinctSubbreeds(): List<String>

    @Query("SELECT DISTINCT location FROM dogs")
    fun getDistinctLocations(): Flow<List<String>>

}