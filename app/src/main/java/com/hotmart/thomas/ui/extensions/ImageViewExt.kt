package com.hotmart.thomas.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hotmart.thomas.R


fun ImageView.setImageResourceFrom(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .error(R.drawable.ic_broken_image)
        .into(this)
}