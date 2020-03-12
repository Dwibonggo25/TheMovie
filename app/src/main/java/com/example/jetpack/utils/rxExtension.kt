package com.example.jetpack.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.jetpack.BASE_IMAGE_URL

@BindingAdapter("imageFromUrl")
fun loadImageView(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()){
        val uri = "$BASE_IMAGE_URL$url"
        Glide.with(imageView.context)
            .load(uri)
            .into(imageView)
    }
}