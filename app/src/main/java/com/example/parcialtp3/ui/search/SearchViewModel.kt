package com.example.parcialtp3.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.data.dao.DogDao
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.domain.model.DogMapper.Companion.mapDogModelToDog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val dao: DogDao) : ViewModel() {
    fun getAvailableDogsByBreed(query: String): LiveData<List<Dog>> {
        return dao.getDogsByBreed(query)
            .map { dogModelList -> mapDogModelToDog(dogModelList) }
            .asLiveData()
    }
}