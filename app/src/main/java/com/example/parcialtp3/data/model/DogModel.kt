package com.example.parcialtp3.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dogs")
data class DogModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int,
    val gender: String,
    val description: String,
    val weight: Double,
    val location: String,
    val breed: MainBreed,
    val images: List<String>,
    val adopter: Adopter?,
    val isAdopted: Boolean,
    val observations: String,
    val isFavorite: Boolean,
) {
    data class Adopter(
        val name: String,
        val phone: String
    )
}

sealed class MainBreed(val breedName: String) {
    object AFFENPINSCHER : MainBreed("affenpinscher")
    object AFRICAN : MainBreed("african")

    object AUSTRALIAN : MainBreed("australian") {
        sealed class SubBreed(subBreedName: String) {
            object KELPIE : SubBreed("kelpie")
            object SHEPHERD : SubBreed("shepherd")
        }
    }

    companion object {
        fun fromString(breedName: String): MainBreed {
            return when (breedName) {
                "affenpinscher" -> AFFENPINSCHER
                "african" -> AFRICAN
                "australian" -> AUSTRALIAN
                else -> throw IllegalArgumentException("Unknown breed name: $breedName")
            }
        }
    }
}
