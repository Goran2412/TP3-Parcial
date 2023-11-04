package com.example.parcialtp3.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>()

    fun setUserName(userName: String) {
        _text.value = userName
    }

    val text: LiveData<String> = _text
}
