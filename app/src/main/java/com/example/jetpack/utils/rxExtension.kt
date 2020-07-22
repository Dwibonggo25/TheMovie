package com.example.jetpack.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.jetpack.BASE_IMAGE_URL
import com.example.jetpack.R

@BindingAdapter("imageFromUrl")
fun loadImageView(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()){
        val uri = "$BASE_IMAGE_URL$url"
        Glide.with(imageView.context)
            .load(uri)
            .into(imageView)
    }else {
        Glide.with(imageView.context)
            .load(R.drawable.ic_no_photo)
            .into(imageView)
    }
}

@BindingAdapter("imageFromDrawable")
fun loadImageView(imageView: ImageView, url: Int?) {
    if (url != 0){
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }else {
        Glide.with(imageView.context)
            .load(R.drawable.ic_no_photo)
            .into(imageView)
    }
}



