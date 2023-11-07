package com.example.parcialtp3.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.data.dao.DogDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FilterDialogViewModel @Inject constructor(private val dao: DogDao) : ViewModel() {

    fun getDistinctBreedsAndSubbreeds(): LiveData<Pair<List<String>, List<String>>>
    {
        val resultLiveData = MediatorLiveData<Pair<List<String>, List<String>>>()

        viewModelScope.launch(Dispatchers.IO) {
            val breeds = dao.getDistinctBreeds()
            val subbreeds = dao.getDistinctSubbreeds()

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

    fun getDistinctLocations(): LiveData<List<String>> {
        return dao.getDistinctLocations().asLiveData()
    }

    fun applyFilters(selectedBreeds: List<String>, selectedLocations: List<String>) {
        // Implement your filter logic using the selected breeds and locations
    }
}