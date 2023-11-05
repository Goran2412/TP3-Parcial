package com.example.parcialtp3.ui.publication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.response.DogBreedsResponse
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.domain.usecase.GetBreedsFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor(private val getBreedsFromApiUseCase: GetBreedsFromApiUseCase) : ViewModel() {

    private val _breedsListState = MutableLiveData<Result<DogBreedsResponse>>()
    val breedsListState: LiveData<Result<DogBreedsResponse>> = _breedsListState


    private val _text = MutableLiveData<String>()
    //TODO: revisar esto, no encontre como resolverlo de otra forma
    fun setDogName(DogName: String) {
        _text.value = DogName
    }

    fun getAllBreeds(){
        _breedsListState.postValue(Result.Loading)
        viewModelScope.launch {
          val breeds = getBreedsFromApiUseCase()
            _breedsListState.postValue(breeds)
        }
    }
    val text: LiveData<String> = _text
}