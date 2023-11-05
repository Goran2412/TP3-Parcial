package com.example.parcialtp3.ui.home


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

    fun testInsert(){
        viewModelScope.launch(Dispatchers.IO) {
            val newDog = DogModel(
                name = "Perrito",
                age = 3,
                gender = "Male",
                description = "Friendly and playful",
                weight = 15.5,
                location = "Shelter",
                breed = MainBreed.AUSTRALIAN,
                images = listOf("image1.jpg", "image2.jpg"),
                adopter = DogModel.Adopter("John Doe", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues"
            )
            dogDao.insert(newDog)
        }
    }


//    fun getAllDogs() {
//        Log.d(TAG, "getAllDogs()")
//        _dogsListState.postValue(Result.Loading)
//        viewModelScope.launch {
//            val booksResult = getAllDogsUseCase()
//            _dogsListState.postValue(booksResult)
//        }
//    }

//    fun getAllBooks() {
//        _dogsListState.postValue(Result.Loading)
//        viewModelScope.launch {
//            val booksResult = getAllDogsUseCase()
//            _dogsListState.postValue(booksResult)
//        }
//    }

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            val newDog = DogModel(
//                name = "Buddy",
//                age = 3,
//                gender = "Male",
//                description = "Friendly and playful",
//                weight = 15.5,
//                location = "Shelter",
//                breed = MainBreed.AUSTRALIAN,
//                images = listOf("image1.jpg", "image2.jpg"),
//                adopter = DogModel.Adopter("John Doe", "555-555-5555"),
//                isAdopted = false,
//                observations = "No known health issues"
//            )
//
//           // dogDao.insert(newDog)
//
//            val coso = dogDao.getAllDogs()
//            Log.d("DOGS", "mis dogs $coso")
//        }
//
//    }

}