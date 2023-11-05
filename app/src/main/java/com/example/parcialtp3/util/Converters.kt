package com.example.parcialtp3.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.parcialtp3.data.model.MainBreed
import com.example.parcialtp3.data.model.DogModel.Adopter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun fromMainBreed(breed: MainBreed): String {
        return breed.breedName
    }

    @TypeConverter
    fun toMainBreed(breedName: String): MainBreed {
        return MainBreed.fromString(breedName) // Implement a function to get MainBreed from the name
    }

    @TypeConverter
    fun fromAdopter(adopter: Adopter?): String? {
        return Gson().toJson(adopter)
    }

    @TypeConverter
    fun toAdopter(adopterJson: String?): Adopter? {
        return Gson().fromJson(adopterJson, Adopter::class.java)
    }

    @TypeConverter
    fun fromImagesList(images: List<String>): String {
        return images.joinToString(",")
    }

    @TypeConverter
    fun toImagesList(imagesString: String): List<String> {
        return imagesString.split(",")
    }

}