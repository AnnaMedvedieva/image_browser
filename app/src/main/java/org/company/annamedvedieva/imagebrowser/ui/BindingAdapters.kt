package org.company.annamedvedieva.imagebrowser.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.company.annamedvedieva.imagebrowser.R
import org.company.annamedvedieva.imagebrowser.data.ImageItem

@BindingAdapter("imageData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ImageItem>?) {
    val adapter = recyclerView.adapter as ImageGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.test)
        .into(imageView)
}