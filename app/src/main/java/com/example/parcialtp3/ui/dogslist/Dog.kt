package com.example.parcialtp3.ui.dogslist

data class Dog (
    val name : String,
    val breed : String,
    val subbreed : String,
    val edad : Int,
    val genero : DogGenders,
    val imageLink: String
)