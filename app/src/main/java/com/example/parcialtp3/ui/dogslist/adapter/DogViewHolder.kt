package com.example.parcialtp3.ui.dogslist.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.parcialtp3.R
import com.example.parcialtp3.ui.dogslist.Dog
import org.w3c.dom.Text

class DogViewHolder(val view : View) : ViewHolder(view) {

    val dogName = view.findViewById<TextView>(R.id.dogName)
    val dogBreed = view.findViewById<TextView>(R.id.dogBreedName)
    val dogSubBreed = view.findViewById<TextView>(R.id.dogSubBreedName)
    val dogAge = view.findViewById<TextView>(R.id.dogAgeText)
    val dogGender = view.findViewById<TextView>(R.id.dogGenderText)

    fun renderizar(perro : Dog) {
        dogName.text = perro.name
        dogBreed.text = perro.breed
        dogSubBreed.text = perro.subbreed
        dogAge.text = perro.edad.toString()
        dogGender.text = perro.genero.toString()

    }

}