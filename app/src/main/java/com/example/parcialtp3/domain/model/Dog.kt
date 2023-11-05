package com.example.parcialtp3.domain.model

import com.example.parcialtp3.data.model.DogModel

data class Dog(val name: String)
fun DogModel.toDomain() = Dog(name)