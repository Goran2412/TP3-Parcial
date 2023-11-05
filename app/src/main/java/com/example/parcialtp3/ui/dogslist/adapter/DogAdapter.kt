package com.example.parcialtp3.ui.dogslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.ui.dogslist.Dog
import com.example.parcialtp3.R

class DogAdapter(private val dogsList: List<Dog>) : RecyclerView.Adapter<DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }


    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = dogsList[position]
        holder.renderizar(item)
    }

    override fun getItemCount(): Int = dogsList.size // Al únicamente hacer un return, puedo declararla así

}