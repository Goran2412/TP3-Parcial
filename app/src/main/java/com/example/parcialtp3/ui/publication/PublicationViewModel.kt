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
class PublicationViewModel @Inject constructor(private val getBreedsFromApiUseCase: GetBreedsFromApiUseCase) :
    ViewModel() {

    private val _breedsListState = MutableLiveData<Result<DogBreedsResponse>>()
    val breedsListState: LiveData<Result<DogBreedsResponse>> = _breedsListState

    private val _breedsList = MutableLiveData<List<BreedWithSubBreeds>>()
    val breedsList: LiveData<List<BreedWithSubBreeds>> = _breedsList

    private val _selectedBreed = MutableLiveData<String>()
    val selectedBreed: LiveData<String> = _selectedBreed

    fun setSelectedBreed(breed: String) {
        _selectedBreed.value = breed
    }


    fun getAllBreeds() {
        _breedsListState.postValue(Result.Loading)
        viewModelScope.launch {
            val breeds = getBreedsFromApiUseCase()
            _breedsListState.postValue(breeds)
            if (breeds is Result.Success) {
                val breedList = breeds.data.message
                val breedsWithSubBreeds = breedList.map { (breedName, subBreeds) ->
                    BreedWithSubBreeds(breedName, subBreeds)
                }
                _breedsList.postValue(breedsWithSubBreeds)
            }
        }
        }
    }


data class BreedWithSubBreeds(
    val breedName: String,
    val subBreeds: List<String>
)