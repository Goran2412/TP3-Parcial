package com.example.parcialtp3.ui.adapter.adapter

import com.example.parcialtp3.domain.model.Dog

//class DogListener(val clickListener: (dog: Dog) -> Unit) {
//    fun onClick(dog: Dog) = clickListener(dog)
//    fun onSaveIconClick(dog: Dog) = clickListener(dog)
//
//}

class DogListener(val clickListener: (dog: Dog, dogId: Int) -> Unit) {
    fun onClick(dog: Dog, dogId: Int) = clickListener(dog, dogId)
   // fun onSaveIconClick(dogId: Int) = clickListener(dogId)
}

class SaveIconListener(val clickListener: (dogId: Int) -> Unit) {
    fun onClick(dogId: Int) = clickListener(dogId)

}