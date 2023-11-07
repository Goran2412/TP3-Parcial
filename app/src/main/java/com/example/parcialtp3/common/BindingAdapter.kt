package com.example.parcialtp3.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.parcialtp3.R

@BindingAdapter("app:isFavourite")
fun setFavoriteIcon(imageView: ImageView, isFavourite: Boolean) {
    val iconResId = if (isFavourite) {
        R.drawable.ic_saved
    } else {
        R.drawable.ic_save
    }
    imageView.setImageResource(iconResId)
}