package com.example.parcialtp3.ui.adapter.adapter

import com.example.parcialtp3.domain.model.Dog

class DogListener(val clickListener: (dog: Dog) -> Unit) {
    fun onClick(dog: Dog) = clickListener(dog)
}