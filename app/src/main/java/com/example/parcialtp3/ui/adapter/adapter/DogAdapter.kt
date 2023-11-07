package com.example.parcialtp3.ui.adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.databinding.ItemDogBinding
import com.example.parcialtp3.domain.model.Dog

class DogAdapter(
    private val clickListener: DogListener,
    private val saveIconListener: SaveIconListener
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
        fun bind(dog: Dog) {
            binding.dog = dog
            binding.dogName.text = dog.name
            binding.dogAgeText.text = dog.age.toString()
            binding.dogBreedName.text = dog.breed
            binding.dogGenderText.text = dog.gender
            binding.dogSubBreedName.text = dog.subbreed
            binding.clickListener = clickListener
            binding.saveIconListener = saveIconListener
            binding.executePendingBindings()
        }
    }

    fun submitList(dogs: List<Dog>) {
        this.dogs = dogs
        notifyDataSetChanged()
    }
}