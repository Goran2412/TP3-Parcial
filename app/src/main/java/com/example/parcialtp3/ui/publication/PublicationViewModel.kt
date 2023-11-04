package com.example.parcialtp3.ui.publication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>()
    //TODO: revisar esto, no encontre como resolverlo de otra forma
    fun setDogName(DogName: String) {
        _text.value = DogName
    }
    val text: LiveData<String> = _text
}