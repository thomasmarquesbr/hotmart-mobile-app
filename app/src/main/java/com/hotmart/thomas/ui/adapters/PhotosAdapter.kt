package com.hotmart.thomas.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hotmart.thomas.R
import com.hotmart.thomas.databinding.PhotosItemAdapterBinding


class PhotosAdapter(
    private val context: Context,
): RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    var photos = listOf<String>()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosAdapter.PhotosViewHolder {
        val binding = PhotosItemAdapterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosAdapter.PhotosViewHolder, position: Int) {
        val item = photos[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = photos.size

    /** ViewHolder **/

    inner class PhotosViewHolder(
        private val binding: PhotosItemAdapterBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            Glide.with(context)
                .load(item)
                .error(R.drawable.ic_broken_image)
                .into(binding.ivImage)
        }

    }

}
