package com.example.parcialtp3.domain.model

import com.example.parcialtp3.data.model.DogModel

class DogMapper {
    companion object {
        fun mapDogModelToDog(dogModelList: List<DogModel>): List<Dog> {
            return dogModelList.map { dogModel ->
                Dog(
                    id = dogModel.id,
                    name = dogModel.name,
                    age= dogModel.age,
                    gender= dogModel.gender,
                    description = dogModel.description,
                    weight = dogModel.weight,
                    location = dogModel.location,
                    breed = dogModel.breed,
                    subbreed = dogModel.subbreed,
                    images = dogModel.images,
                    adopterModel = dogModel.adopterModel,
                    isAdopted = dogModel.isAdopted,
                    observations = dogModel.observations,
                    isFavourite= dogModel.isFavourite)
            }
        }
    }
}