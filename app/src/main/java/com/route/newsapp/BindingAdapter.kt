package com.route.newsapp

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("UrlToImage")
fun loadImage(imageView : ImageView , url: String){
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}

@BindingAdapter("app:changeBackColor")
fun changeBackColor(cardView : CardView , color : Int){
cardView.setBackgroundColor(
    cardView.context.getColor(color)
)
}

@BindingAdapter("app:imageResource")
fun setImageResource(image :ImageView , id : Int){
    image.setImageResource(id)
}