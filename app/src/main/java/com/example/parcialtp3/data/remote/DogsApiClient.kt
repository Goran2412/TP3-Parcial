package com.example.parcialtp3.data.remote

import com.example.parcialtp3.data.response.DogBreedsResponse
import retrofit2.http.GET

interface DogsApiClient {
    @GET("breeds/list/all")
    suspend fun getBreeds(): DogBreedsResponse
}