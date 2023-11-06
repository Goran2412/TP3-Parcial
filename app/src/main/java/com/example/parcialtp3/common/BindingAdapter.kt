package com.example.parcialtp3.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.parcialtp3.R

@BindingAdapter("app:isFavourite")
fun setFavoriteIcon(imageView: ImageView, isFavourite: Boolean) {
    val iconResId = if (isFavourite) {
        R.drawable.ic_saved // Change to ic_saved when isFavourite is true
    } else {
        R.drawable.ic_save // Change to ic_save when isFavourite is false
    }
    imageView.setImageResource(iconResId)
}