package com.hotmart.thomas.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hotmart.thomas.databinding.PhotosItemAdapterBinding
import com.hotmart.thomas.ui.extensions.setImageResourceFrom


class PhotosAdapter: RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

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
            binding.ivImage.setImageResourceFrom(item)
        }

    }

}
