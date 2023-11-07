package com.example.parcialtp3.ui.adapter.adapter

import com.example.parcialtp3.domain.model.Dog







class DogListener(val clickListener: (dog: Dog, dogId: Int) -> Unit) {
    fun onClick(dog: Dog, dogId: Int) = clickListener(dog, dogId)

}

class SaveIconListener(val clickListener: (dogId: Int) -> Unit) {
    fun onClick(dogId: Int) = clickListener(dogId)

}