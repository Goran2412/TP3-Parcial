package com.example.parcialtp3.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.databinding.DogImageItemBinding
import com.squareup.picasso.Picasso

class DogImageAdapter(private val dogImages: List<DogImage>) : RecyclerView.Adapter<DogImageAdapter.DogImageViewHolder>() {

    class DogImageViewHolder(private val binding: DogImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dogImage: DogImage) {
            Picasso.get().load(dogImage.imageUrl).into(binding.dogImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogImageViewHolder {
        val binding = DogImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogImageViewHolder, position: Int) {
        holder.bind(dogImages[position])
    }

    override fun getItemCount() = dogImages.size
}

