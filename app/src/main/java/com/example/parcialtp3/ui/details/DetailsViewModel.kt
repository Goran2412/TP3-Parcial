package com.example.parcialtp3.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.parcialtp3.domain.model.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


private const val TAG = "DetailsViewModel"

@HiltViewModel
class DetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    val dog: Dog = savedStateHandle["dog"]!!

    init {
        Log.d(TAG, "mi dog $dog")
    }

    fun showDog(){
        Log.d(TAG, "mi dog $dog")
    }
}