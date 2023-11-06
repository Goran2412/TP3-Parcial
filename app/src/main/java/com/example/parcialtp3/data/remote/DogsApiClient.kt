package com.example.parcialtp3.data.remote

import com.example.parcialtp3.data.response.DogBreedsResponse
import com.example.parcialtp3.data.response.DogImagesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApiClient {
    @GET("breeds/list/all")
    suspend fun getBreeds(): DogBreedsResponse

    //pido imagenes
    @GET("breed/{breed}/images/random/{count}")
    suspend fun getRandomImages(@Path("breed") breed: String, @Path("count") count: Int): DogImagesResponse
}