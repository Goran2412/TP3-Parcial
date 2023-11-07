package com.example.parcialtp3.ui.publication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.model.DogModel
import com.example.parcialtp3.data.response.DogBreedsResponse
import com.example.parcialtp3.data.response.DogImagesResponse
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.domain.usecase.AddDogToAdoptionListUseCase
import com.example.parcialtp3.domain.usecase.GetBreedsFromApiUseCase
import com.example.parcialtp3.ui.publication.components.BreedWithSubBreeds
import com.example.parcialtp3.domain.usecase.GetRandomImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "PublicationViewModel"

@HiltViewModel
class PublicationViewModel @Inject constructor(
    private val getBreedsFromApiUseCase: GetBreedsFromApiUseCase,
    private val addDogToAdoptionListUseCase: AddDogToAdoptionListUseCase,
    private val getRandomImagesUseCase: GetRandomImagesUseCase

) : ViewModel() {
    private val _breedsListState = MutableLiveData<Result<DogBreedsResponse>>()
    val breedsListState: LiveData<Result<DogBreedsResponse>> = _breedsListState

    private val _breedsList = MutableLiveData<List<BreedWithSubBreeds>>()
    val breedsList: LiveData<List<BreedWithSubBreeds>> = _breedsList

    private val _selectedBreed = MutableLiveData<String>()
    val selectedBreed: LiveData<String> = _selectedBreed

    private val _randomImages = MutableLiveData<Result<DogImagesResponse>>()
    val randomImages: LiveData<Result<DogImagesResponse>>
        get() = _randomImages

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean>
        get() = _isFormValid

    private val _isBreedValid = MutableLiveData<Boolean>()
    val isBreedValid: LiveData<Boolean>
        get() = _isBreedValid

    private val _isSubBreedValid = MutableLiveData<Boolean>()
    val isSubBreedValid: LiveData<Boolean>
        get() = _isSubBreedValid

    private val _isValidName = MutableLiveData<Boolean>()
    val isValidName: LiveData<Boolean>
        get() = _isValidName

    private val _isValidAge = MutableLiveData<Boolean>()
    val isValidAge: LiveData<Boolean>
        get() = _isValidAge

    private val _isValidGender = MutableLiveData<Boolean>()
    val isValidGender: LiveData<Boolean>
        get() = _isValidGender

    private val _isValidDescription = MutableLiveData<Boolean>()
    val isValidDescription: LiveData<Boolean>
        get() = _isValidDescription

    private val _isValidWeight = MutableLiveData<Boolean>()
    val isValidWeight: LiveData<Boolean>
        get() = _isValidWeight

    private val _isValidLocation = MutableLiveData<Boolean>()
    val isValidLocation: LiveData<Boolean>
        get() = _isValidLocation

    init {
        _isFormValid.value = false
        _isBreedValid.value = false
        _isSubBreedValid.value = true
        _isValidName.value = false
        _isValidAge.value = false
        _isValidGender.value = false
        _isValidDescription.value = false
        _isValidWeight.value = false
        _isValidLocation.value = false
    }

    fun validateBreed(breed: String) {
        _isBreedValid.value = breed.isNotEmpty()
        validateForm()
    }

    fun validateSubBreed(subBreed: String) {
        _isSubBreedValid.value = subBreed.isNotEmpty()
        validateForm()
    }

    fun validateName(name: String, validationCallback: (Boolean) -> Unit) {
        _isValidName.value = name.isNotEmpty()
        val isValid = name.isNotEmpty()
        validationCallback(isValid)
        validateForm()
    }

    fun validateAge(age: String, validationCallback: (Boolean) -> Unit) {
        _isValidAge.value = age.isNotEmpty()
        val isValid = age.isNotEmpty()
        validationCallback(isValid)
        validateForm()
    }

    fun validateGender(gender: String, validationCallback: (Boolean) -> Unit) {
        _isValidGender.value = gender.isNotEmpty()
        val isValid = gender.isNotEmpty()
        validationCallback(isValid)
        validateForm()
    }

    fun validateDescription(description: String, validationCallback: (Boolean) -> Unit) {
        _isValidDescription.value = description.isNotEmpty()
        val isValid = description.isNotEmpty()
        validationCallback(isValid)
        validateForm()
    }

    fun validateWeight(weight: String, validationCallback: (Boolean) -> Unit) {
        _isValidWeight.value = weight.isNotEmpty()
        val isValid = weight.isNotEmpty()
        validationCallback(isValid)
        validateForm()
    }

    fun validateLocation(location: String, validationCallback: (Boolean) -> Unit) {
        _isValidLocation.value = location.isNotEmpty()
        val isValid = location.isNotEmpty()
        validationCallback(isValid)
        validateForm()
    }

    fun validateForm() {
        _isFormValid.value = _isBreedValid.value == true && _isSubBreedValid.value == true &&
                _isValidName.value == true && _isValidAge.value == true &&
                _isValidDescription.value == true && _isValidWeight.value == true && _isValidLocation.value == true

        Log.d(TAG, "isFormValid ${isFormValid.value}")
    }

    fun validateField(field: String, validationCallback: (Boolean) -> Unit) {
        val isValid = field.isNotEmpty()
        validationCallback(isValid)
        validateForm()
    }


    fun setSelectedBreed(breed: String) {
        _selectedBreed.value = breed
    }

    fun setSelectedGender(gender: String) {
        _isValidGender.value = gender.isNotEmpty()
        validateForm()
    }

    fun getRandomImages(breed: String, count: Int = 5) {
        viewModelScope.launch {
            val result = getRandomImagesUseCase(breed.lowercase(), count)
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
                    BreedWithSubBreeds(
                        breedName.titleCaseFirstChar(),
                        subBreeds.map { it.titleCaseFirstChar() }
                    )
                }
                _breedsList.postValue(breedsWithSubBreeds)
            }
        }
    }

    private fun String.titleCaseFirstChar(): String {
        return replaceFirstChar { it.titlecase() }
    }

    fun insertDog(dog: DogModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = addDogToAdoptionListUseCase(dog)
            if (result is Result.Success) {
              Log.d(TAG, "insertedDog")
            } else if (result is Result.Error) {
                Log.d(TAG, "error ${result.message}")
            }
        }
    }

}


