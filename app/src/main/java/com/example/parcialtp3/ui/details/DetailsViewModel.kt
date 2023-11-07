package com.example.parcialtp3.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.data.dao.DogDao
import com.example.parcialtp3.data.model.DogModel
import com.example.parcialtp3.domain.model.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "DetailsViewModel"

@HiltViewModel
class DetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val dogDao: DogDao) : ViewModel() {
    val dog: Dog = savedStateHandle["dog"] ?: Dog(id=1,name = "Fido",
        age = 7,
        gender = "Macho",
        description = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Cumque laudantium iste accusantium id asperiores assumenda labore minima aut ut at ducimus, possimus, dolores tempore amet magni mollitia autem blanditiis nulla asperiores assumenda labore minima aut ut at ducimus",
        weight = 11.5,
        location = "Buenos Aires",
        breed = "Labrador",
        subbreed = "Retriever",
        images = listOf("https://images.dog.ceo/breeds/labrador/n02099712_3301.jpg",
            "https://images.dog.ceo/breeds/labrador/n02099712_607.jpg",
            "https://images.dog.ceo/breeds/labrador/n02099712_8242.jpg"),
        adopterModel = DogModel.AdopterModel("Juan Perez", "12345678"),
        isAdopted = false,
        observations = "--",
        isFavourite = false)


    fun showDog(){
        Log.d(TAG, "mi dog $dog")
    }

    fun adoptDog() {
        Log.d(TAG, "updateDog")
        viewModelScope.launch(Dispatchers.IO) {
            val dog = dogDao.getDogById(dog.id)
            if (dog != null) {
                dogDao.adoptDog(dog.id)
            }
        }
    }

}