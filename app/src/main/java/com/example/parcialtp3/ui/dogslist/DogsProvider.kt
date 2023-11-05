package com.example.parcialtp3.ui.dogslist

class DogsProvider {
    companion object {
        var mainPageDogs = listOf<Dog>(
            Dog("Kaisha", "Labrador", "Inglés", 7, DogGenders.HEMBRA),
            Dog("Milo", "Labrador", "Arlequín", 1, DogGenders.MACHO),
            Dog("Dorón", "Labrador", "Americano", 3, DogGenders.MACHO),
        )
    }
}