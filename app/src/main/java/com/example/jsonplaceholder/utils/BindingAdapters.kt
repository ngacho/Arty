package com.example.jsonplaceholder.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.jsonplaceholder.R
import com.squareup.picasso.Picasso


@BindingAdapter("photoUrl")
fun ImageView.loadImage(url: String?) {
    url?.let {
        Picasso.get().load(it)
            .placeholder(R.drawable.unsplash)
            .into(this)
    }
}
