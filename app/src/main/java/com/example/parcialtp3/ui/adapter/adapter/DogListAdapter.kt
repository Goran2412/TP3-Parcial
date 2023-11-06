package com.example.parcialtp3.ui.adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.R

//class DogAdapter(private val dogsList: List<Dog>, private val context: Context) : RecyclerView.Adapter<DogViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
//        val item = dogsList[position]
//        holder.renderizar(item)
//    }
//
//    override fun getItemCount(): Int = dogsList.size // Al únicamente hacer un return, puedo declararla así
//
//}

import coil.load
import com.example.parcialtp3.databinding.ItemDogBinding

class DogListAdapter(val clickListener: DogListener) : ListAdapter<Dog, DogListAdapter.DogViewHolder>(DogDiffCallback()) {
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
    return DogViewHolder(
        ItemDogBinding.inflate(
            LayoutInflater.from(
                parent.context
            )
        )
    )
}

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    inner class DogViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(perro: Dog, clickListener: DogListener) {
            binding.dog = perro
            binding.dogName.text = perro.name
            binding.dogBreedName.text = perro.breed
            binding.dogSubBreedName.text = perro.subbreed
            "${perro.age} años / ".also { binding.dogAgeText.text = it }
            binding.dogGenderText.text = perro.gender.toString()
            binding.elementDogRV.load(perro.images?.get(0))
            binding.clickListener = clickListener

            var guardado = false

            binding.saveIcon.setOnClickListener {
                if (guardado) {
                    binding.saveIcon.setImageResource(R.drawable.ic_save)
                } else {
                    binding.saveIcon.setImageResource(R.drawable.ic_saved)
                }
                guardado = !guardado
            }
        }
    }
}



