package com.example.parcialtp3.ui.publication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.response.DogBreedsResponse
import com.example.parcialtp3.data.response.DogImagesResponse
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.domain.usecase.GetBreedsFromApiUseCase
import com.example.parcialtp3.domain.usecase.GetRandomImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor(
    private val getBreedsFromApiUseCase: GetBreedsFromApiUseCase,
    private val getRandomImagesUseCase: GetRandomImagesUseCase
) : ViewModel() {

    private val _breedsListState = MutableLiveData<Result<DogBreedsResponse>>()
    val breedsListState: LiveData<Result<DogBreedsResponse>> = _breedsListState

    private val _breedsList = MutableLiveData<List<BreedWithSubBreeds>>()
    val breedsList: LiveData<List<BreedWithSubBreeds>> = _breedsList

    private val _selectedBreed = MutableLiveData<String>()
    val selectedBreed: LiveData<String> = _selectedBreed

    private val _randomImages = MutableLiveData<Result<DogImagesResponse>>()
    val randomImages: LiveData<Result<DogImagesResponse>> get() = _randomImages // no se si funciona, hasta aca llegue, teoricamente deberias podes traer una lista de url con imagenes


    fun setSelectedBreed(breed: String) {
        _selectedBreed.value = breed
    }


    //TODO: probar como usar esto (si funciona), hasta ahora todo es dolor
    fun getRandomImages(breed: String, count: Int = 5) {
        viewModelScope.launch {
            val result = getRandomImagesUseCase(breed, count)
            if (result is Result.Success) {
                _randomImages.postValue(result)
            }
        }
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