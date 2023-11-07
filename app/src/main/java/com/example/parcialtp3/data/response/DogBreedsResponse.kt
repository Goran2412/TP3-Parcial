package com.example.parcialtp3.data.response

data class DogBreedsResponse(
    val message: Map<String, List<String>>,
    val status: String
)