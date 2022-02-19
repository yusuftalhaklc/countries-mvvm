package com.yusuftalhaklc.countriesudemy.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusuftalhaklc.countriesudemy.R
import kotlinx.android.synthetic.main.item_row.view.*

fun ImageView.downloadFormUrl(url:String, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions
        .placeholderOf(progressDrawable)
        .error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeholderProgressBar(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply{
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
@BindingAdapter("android:downloadurl")
fun downloadImage(view:ImageView, url:String?){
    url?.let{
        view.downloadFormUrl(it, placeholderProgressBar(view.context))
    }

}