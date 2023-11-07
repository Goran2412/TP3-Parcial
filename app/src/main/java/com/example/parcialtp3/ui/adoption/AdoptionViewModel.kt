package com.example.parcialtp3.ui.adoption

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.data.dao.DogDao
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.domain.usecase.GetAdoptedDogsUseCase
import com.example.parcialtp3.domain.usecase.GetFavoriteDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "AdoptionViewModel"
@HiltViewModel
class AdoptionViewModel @Inject constructor(
    private val getAdoptedDogsUseCase: GetAdoptedDogsUseCase
) : ViewModel() {

    private val _dogsListState = MutableLiveData<Result<List<Dog>>>()
    val dogsListState: LiveData<Result<List<Dog>>> = _dogsListState

    init {
        Log.d(TAG, "init")
        viewModelScope.launch {
            getAdoptedDogsUseCase().collect { result ->
                _dogsListState.value = result
            }
        }
    }
}


