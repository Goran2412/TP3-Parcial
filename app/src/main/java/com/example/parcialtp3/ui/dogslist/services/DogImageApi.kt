package com.example.parcialtp3.ui.dogslist.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogImageApi {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<ImageResponse>

    @GET("breed/{breed}/images/random")
    suspend fun getOneDogImageByBreed(@Path("breed") breed: String): Response<ImageResponse>
}
