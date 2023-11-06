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

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean>
        get() = _isFormValid

    private val _isBreedValid = MutableLiveData<Boolean>()
    val isBreedValid: LiveData<Boolean>
        get() = _isBreedValid

    init {
        _isFormValid.value = false
        _isBreedValid.value = false
    }

    fun validateBreed(breed: String) {
        _isBreedValid.value = breed.isNotEmpty()
        validateForm()
    }
    private val _isSubBreedValid = MutableLiveData<Boolean>()
    val isSubBreedValid: LiveData<Boolean>
        get() = _isSubBreedValid

    // Existing code...

    fun validateSubBreed(subBreed: String) {
        _isSubBreedValid.value = subBreed.isNotEmpty()
        validateForm()
    }

    fun validateForm() {
        _isFormValid.value = _isBreedValid.value == true /*&& other validation conditions*/
    }


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
                    BreedWithSubBreeds(
                        breedName.titleCaseFirstChar(),
                        subBreeds.map { it.titleCaseFirstChar() })
                }
                _breedsList.postValue(breedsWithSubBreeds)
            }
        }
    }

    private fun String.titleCaseFirstChar(): String {
        return replaceFirstChar { it.titlecase() }
    }
}


data class BreedWithSubBreeds(
    val breedName: String,
    val subBreeds: List<String>
)