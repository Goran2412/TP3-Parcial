package com.example.parcialtp3.domain.model

import android.os.Parcelable
import com.example.parcialtp3.data.model.DogModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dog(
    val id : Int,
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
    val isFavourite: Boolean
): Parcelable

fun DogModel.toDomain() = Dog(
    id,
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
    observations,
    isFavourite
)
