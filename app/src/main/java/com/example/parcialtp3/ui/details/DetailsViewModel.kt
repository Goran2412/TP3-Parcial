package com.example.parcialtp3.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.data.dao.DogDao
import com.example.parcialtp3.domain.model.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "DetailsViewModel"

@HiltViewModel
class DetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val dogDao: DogDao) : ViewModel() {
    val dog: Dog = savedStateHandle["dog"]!!


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