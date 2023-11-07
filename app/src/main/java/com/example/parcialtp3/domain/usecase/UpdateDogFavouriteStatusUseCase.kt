package com.example.parcialtp3.domain.usecase

import com.example.parcialtp3.domain.repository.DogsRepository
import javax.inject.Inject

class UpdateDogFavouriteStatusUseCase @Inject constructor(private val repository: DogsRepository) {
    suspend operator fun invoke(dogId: Int, isFavourite: Boolean) {
//        // Fetch the dog with the specified ID from the repository
//        val dog = repository.getDogById(dogId)
//        if (dog != null) {
//            // Update the isFavourite property
//            dog.isFavourite = isFavourite
//            // Save the updated dog to the repository
//            repository.updateDog(dog)
//        }
    }
}