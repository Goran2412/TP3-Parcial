package com.example.parcialtp3.ui.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.data.dao.DogDao
import com.example.parcialtp3.domain.usecase.GetAllDogsUseCase
import com.example.parcialtp3.domain.model.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.model.DogModel
import com.example.parcialtp3.data.model.MainBreed
import kotlinx.coroutines.Dispatchers

private const val TAG = "HomeViewModel"
@HiltViewModel
class HomeViewModel @Inject constructor(val dogDao: DogDao, private val getAllDogsUseCase: GetAllDogsUseCase) : ViewModel() {

    private val _dogsListState = MutableLiveData<Result<List<Dog>>>()
    val dogsListState: LiveData<Result<List<Dog>>> = _dogsListState

    init {
        viewModelScope.launch {
            getAllDogsUseCase().collect { result ->
                _dogsListState.value = result
            }
        }
    }

    fun updateDogFavouriteStatus(dogId: Int) {
        Log.d(TAG, "updateDog")
        viewModelScope.launch(Dispatchers.IO) {
            val dog = dogDao.getDogById(dogId)
            if (dog != null) {
                val value = dog.isFavourite
                dog.isFavourite = !value
                dogDao.updateDog(dog)
            }
        }
    }

    fun testInsert(){
        viewModelScope.launch(Dispatchers.IO) {
            val newDog = DogModel(
                name = "Israel",
                age = 3,
                gender = "Male",
                description = "Friendly and playful",
                weight = 15.5,
                location = "Entre Rios",
                breed = "Beagle",
                images = listOf("https://images.dog.ceo/breeds/airedale/n02096051_6799.jpg"),
                adopterModel = DogModel.AdopterModel("John Doe", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            )
            dogDao.insert(newDog)
        }
    }

}