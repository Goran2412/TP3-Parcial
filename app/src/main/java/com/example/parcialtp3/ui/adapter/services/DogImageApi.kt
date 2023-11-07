package com.example.parcialtp3.ui.adapter.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogImageApi {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<ImageResponse>

    @GET("breed/{breed}/images/random") // en principio se va a usar la raza de la clase Dog
    suspend fun getOneDogImageByBreed(@Path("breed") breed: String): Response<ImageResponse>
}
