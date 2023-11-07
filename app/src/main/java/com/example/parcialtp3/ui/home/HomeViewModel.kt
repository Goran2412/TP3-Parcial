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
        val dogs = listOf(
            DogModel(
                name = "Israel",
                age = 3,
                gender = "Male",
                description = "Buena onda",
                weight = 15.4,
                location = "Buenos Aires",
                breed = "Beagle",
                images = listOf("https://images.dog.ceo/breeds/beagle/n02088364_15111.jpg","https://images.dog.ceo/breeds/beagle/n02088364_5282.jpg"),
                adopterModel = DogModel.AdopterModel("Juan carlos", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "Max",
                age = 5,
                gender = "Male",
                description = "Un copado",
                weight = 20.0,
                location = "Cordoba",
                breed = "Akita",
                images = listOf("https://images.dog.ceo/breeds/akita/Akita_inu_blanc.jpg","https://images.dog.ceo/breeds/akita/512px-Akita_inu.jpg"),
                adopterModel = DogModel.AdopterModel("Lucas", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "Juana",
                age = 6,
                gender = "Female",
                description = "Mendoza",
                weight = 53.0,
                location = "Buenos Aires",
                breed = "Boxer",
                images = listOf("https://images.dog.ceo/breeds/boxer/n02108089_1357.jpg","https://images.dog.ceo/breeds/boxer/n02108089_1626.jpg"),
                adopterModel = DogModel.AdopterModel("Maria", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "Riquelme",
                age = 3,
                gender = "Male",
                description = "Buena onda",
                weight = 2.2,
                location = "Buenos Aires",
                breed = "Chihuahua",
                images = listOf("https://images.dog.ceo/breeds/chihuahua/n02085620_8558.jpg","https://images.dog.ceo/breeds/chihuahua/n02085620_8636.jpg"),
                adopterModel = DogModel.AdopterModel("Sebastian", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "Pipi",
                age = 7,
                gender = "Female",
                description = "Buena onda",
                weight = 8.8,
                location = "Cordoba",
                breed = "Husky",
                images = listOf("https://images.dog.ceo/breeds/husky/n02110185_11635.jpg","https://images.dog.ceo/breeds/husky/n02110185_11636.jpg"),
                adopterModel = DogModel.AdopterModel("Marcos", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "Pupu",
                age = 2,
                gender = "Male",
                description = "Buena onda",
                weight = 3.6,
                location = "Buenos Aires",
                breed = "Pug",
                images = listOf("https://images.dog.ceo/breeds/pug/n02110958_14647.jpg","https://images.dog.ceo/breeds/pug/n02110958_14017.jpg"),
                adopterModel = DogModel.AdopterModel("Ana", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "Pepe",
                age = 8,
                gender = "Male",
                description = "Buena onda",
                weight = 15.5,
                location = "Mendoza",
                breed = "Pitbull",
                images = listOf("https://images.dog.ceo/breeds/pitbull/pitbull_dog.jpg","https://images.dog.ceo/breeds/pitbull/IMG_20190826_121528_876.jpg"),
                adopterModel = DogModel.AdopterModel("Julia", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "Carlos",
                age = 1,
                gender = "Male",
                description = "Buena onda",
                weight = 8.4,
                location = "Cordoba",
                breed = "Shiba",
                images = listOf("https://images.dog.ceo/breeds/shiba/shiba-8.jpg","https://images.dog.ceo/breeds/shiba/shiba-16.jpg"),
                adopterModel = DogModel.AdopterModel("Julieta", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "Valeria",
                age = 5,
                gender = "Female",
                description = "Buena onda",
                weight = 5.5,
                location = "Buenos Aires",
                breed = "Pomeranian",
                images = listOf("https://images.dog.ceo/breeds/pomeranian/n02112018_6319.jpg","https://images.dog.ceo/breeds/pomeranian/n02112018_9753.jpg"),
                adopterModel = DogModel.AdopterModel("Sofia", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "Reina",
                age = 7,
                gender = "Female",
                description = "Buena onda",
                weight = 1.5,
                location = "Mendoza",
                breed = "Labrador",
                images = listOf("https://images.dog.ceo/breeds/labrador/n02099712_511.jpg","https://images.dog.ceo/breeds/labrador/n02099712_4759.jpg"),
                adopterModel = DogModel.AdopterModel("Maria", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            ),
            DogModel(
                name = "El mejor",
                age = 10,
                gender = "Male",
                description = "Cordoba",
                weight = 4.6,
                location = "Buenos Aires",
                breed = "Dalmatian",
                images = listOf("https://images.dog.ceo/breeds/dalmatian/cooper1.jpg","https://images.dog.ceo/breeds/dalmatian/cooper2.jpg"),
                adopterModel = DogModel.AdopterModel("Jorge", "555-555-5555"),
                isAdopted = false,
                observations = "No known health issues",
                subbreed = null,
                isFavourite = false
            )
        )

        viewModelScope.launch(Dispatchers.IO) {
            for (dog in dogs) {
                dogDao.insert(dog)
            }
        }
    }

}