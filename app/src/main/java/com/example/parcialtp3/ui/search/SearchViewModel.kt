package com.example.parcialtp3.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.data.dao.DogDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val dao: DogDao) : ViewModel() {
    fun searchDistinctBreedsAndSubbreeds(query: String): LiveData<Pair<List<String>, List<String>>> {
        val resultLiveData = MediatorLiveData<Pair<List<String>, List<String>>>()

        viewModelScope.launch(Dispatchers.IO) {
            val breeds = dao.searchBreeds("%$query%")
            val subbreeds = dao.searchSubbreeds("%$query%")

            withContext(Dispatchers.Main) {
                val breedsLiveData = MutableLiveData<List<String>>()
                val subbreedsLiveData = MutableLiveData<List<String>>()
                breedsLiveData.value = breeds
                subbreedsLiveData.value = subbreeds

                resultLiveData.addSource(breedsLiveData) { updatedBreeds ->
                    val subbreeds = subbreedsLiveData.value ?: emptyList()
                    resultLiveData.value = Pair(updatedBreeds, subbreeds)
                }

                resultLiveData.addSource(subbreedsLiveData) { updatedSubbreeds ->
                    val breeds = breedsLiveData.value ?: emptyList()
                    resultLiveData.value = Pair(breeds, updatedSubbreeds)
                }
            }
        }
        return resultLiveData
    }
}