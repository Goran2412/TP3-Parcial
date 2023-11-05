package com.example.parcialtp3.domain.model

import com.example.parcialtp3.data.model.DogModel
import com.example.parcialtp3.data.model.MainBreed

data class Dog(
    val name: String,
    val age: Int,
    val gender: String,
    val description: String,
    val weight: Double,
    val location: String,
    val breed: MainBreed,
    val images: List<String>,
    val adopter: DogModel.Adopter?,
    val isAdopted: Boolean,
    val observations: String,
   // val isFavorite: Boolean
)
fun DogModel.toDomain() = Dog(name,age,gender,description,weight,location,breed,images,adopter,isAdopted,observations
   //,isFavorite
)
