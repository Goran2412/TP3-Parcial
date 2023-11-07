package com.example.parcialtp3.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "dogs")
data class DogModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String?,
    val age: Int?,
    val gender: String?,
    val description: String?,
    val weight: Double?,
    val location: String?,
    val breed: String?,
    val subbreed: String?,
    val images: List<String>?,
    val adopterModel: AdopterModel?,
    val isAdopted: Boolean?,
    val observations: String?,
    var isFavourite: Boolean
) {
    @Parcelize
    data class AdopterModel(
        val name: String,
        val phone: String
    ): Parcelable
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
