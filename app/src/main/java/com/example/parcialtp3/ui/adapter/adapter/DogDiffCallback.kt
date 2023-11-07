package com.example.parcialtp3.ui.adapter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.parcialtp3.domain.model.Dog

class DogDiffCallback : DiffUtil.ItemCallback<Dog>() {
    override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem == newItem
    }
}