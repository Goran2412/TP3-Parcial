package com.example.parcialtp3.ui.adapter.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.domain.model.Dog
import com.example.parcialtp3.R
import coil.load
import com.example.parcialtp3.databinding.ItemDogBinding

private const val TAG = "DogListAdapter"

class DogListAdapter(
    val clickListener: DogListener,
    val saveIconListener: SaveIconListener,
    val showSaveIcon: Boolean = true
) : ListAdapter<Dog, DogListAdapter.DogViewHolder>(DogDiffCallback()) {
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
        holder.bind(item, clickListener)

    }

    inner class DogViewHolder(private val binding: ItemDogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(perro: Dog, clickListener: DogListener) {

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

//            binding.saveIcon.setOnClickListener {
//                if (guardado) {
//                   // clickListener.favouriteIconListener(perro.id)
//                    Log.d(TAG, "mi id ${perro.id}")
//                    binding.saveIcon.setImageResource(R.drawable.ic_save)
//                 //   clickListener.onClick(perro, perro.id)
//                } else {
//                    binding.saveIcon.setImageResource(R.drawable.ic_saved)
//                    Log.d(TAG, "mi id ${perro.id}")
//                 //   clickListener.onClick(perro, perro.id)
//                }
//
//              //  clickListener.onClick(perro, perro.id)
//                guardado = !guardado
//            }
        }
    }
}



