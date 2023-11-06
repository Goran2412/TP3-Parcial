package com.example.parcialtp3.ui.adapter.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.parcialtp3.R
import com.example.parcialtp3.ui.adapter.Dog

class DogViewHolder(val view : View) : ViewHolder(view) {

    private val saveIcon: ImageView = itemView.findViewById(R.id.saveIcon)

    val dogName = view.findViewById<TextView>(R.id.dogName)
    val dogBreed = view.findViewById<TextView>(R.id.dogBreedName)
    val dogSubBreed = view.findViewById<TextView>(R.id.dogSubBreedName)
    val dogAge = view.findViewById<TextView>(R.id.dogAgeText)
    val dogGender = view.findViewById<TextView>(R.id.dogGenderText)
    val dogImage = view.findViewById<ImageView>(R.id.elementDogRV)

    fun renderizar(perro : Dog) {
        dogName.text = perro.name
        dogBreed.text = perro.breed
        dogSubBreed.text = perro.subbreed
        dogAge.text = perro.edad.toString() + " años / "
        dogGender.text = perro.genero.toString()
        dogImage.load(perro.imageLink)

        var guardado = false

        saveIcon.setOnClickListener {
            if (guardado) {
                saveIcon.setImageResource(R.drawable.ic_save)
            } else {
                saveIcon.setImageResource(R.drawable.ic_saved)
            }
            guardado = !guardado
        }

    }

}