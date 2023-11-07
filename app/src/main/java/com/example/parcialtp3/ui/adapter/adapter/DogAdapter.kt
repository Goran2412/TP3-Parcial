package com.example.parcialtp3.ui.adapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.databinding.ItemDogBinding
import com.example.parcialtp3.domain.model.Dog
import coil.load

class DogAdapter(
    private val clickListener: DogListener,
    private val saveIconListener: SaveIconListener,
    val showSaveIcon: Boolean = true
) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {
    private var dogs: List<Dog> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDogBinding.inflate(layoutInflater, parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogs[position]
        holder.bind(dog)
    }

    override fun getItemCount(): Int {
        return dogs.size
    }

    inner class DogViewHolder(private val binding: ItemDogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(perro: Dog) {
            if (showSaveIcon) {
                binding.saveIcon.visibility = View.VISIBLE
            } else {
                binding.saveIcon.visibility = View.INVISIBLE
            }

            binding.dog = perro
            binding.dogName.text = perro.name
            binding.dogBreedName.text = perro.breed
            binding.dogSubBreedName.text = perro.subbreed
            "${perro.age} años / ".also { binding.dogAgeText.text = it }
            binding.dogGenderText.text = perro.gender.toString()
            binding.elementDogRV.load(perro.images?.get(0))
            binding.clickListener = clickListener
            binding.saveIconListener = saveIconListener
            var guardado = false
        }
    }

    fun submitList(dogs: List<Dog>) {
        this.dogs = dogs
        notifyDataSetChanged()
    }
}