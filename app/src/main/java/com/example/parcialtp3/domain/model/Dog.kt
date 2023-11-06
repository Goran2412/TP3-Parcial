package com.example.parcialtp3.domain.model

import com.example.parcialtp3.data.model.DogModel
import com.example.parcialtp3.data.model.MainBreed

data class Dog(
    val name: String?,
    val age: Int?,
    val gender: String?,
    val description: String?,
    val weight: Double?,
    val location: String?,
    val breed: String?,
    val subbreed: String?,
    val images: List<String>?,
    val adopterModel: DogModel.AdopterModel?,
    val isAdopted: Boolean?,
    val observations: String?,
   // val isFavorite: Boolean
)
fun DogModel.toDomain() = Dog(
    name,
    age,
    gender,
    description,
    weight,
    location,
    breed,
    subbreed,
    images ?: emptyList(), // Provide a default empty list if images is null
    adopterModel,
    isAdopted,
    observations
    //,isFavorite
)
