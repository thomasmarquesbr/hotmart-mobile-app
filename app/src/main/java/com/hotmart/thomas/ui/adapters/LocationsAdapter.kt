package com.hotmart.thomas.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hotmart.domain.models.presentation.Location
import com.hotmart.thomas.R
import com.hotmart.thomas.databinding.LocationsItemAdapterBinding
import kotlin.math.roundToInt


class LocationsAdapter(
    private val context: Context,
    val onItemClicked: (Location) -> Unit
): RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder>() {

    var locations = listOf<Location>()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val binding = LocationsItemAdapterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val item = locations[position]
        holder.bind(item, onClick = { onItemClicked(it) })
    }

    override fun getItemCount(): Int = locations.size

    /** ViewHolder **/

    inner class LocationsViewHolder(
        private val binding: LocationsItemAdapterBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Location, onClick: (Location) -> Unit) {
            binding.tvName.text = item.name
            binding.tvType.text = item.type
            binding.tvReview.text = item.review.toString()
            binding.root.setOnClickListener { onClick(item) }
            Glide.with(context)
                .load(item.getImageUrl())
                .error(R.drawable.ic_broken_image)
                .into(binding.ivImage)
            when (item.review.roundToInt()) {
                1 -> {
                    binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar2.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar3.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar4.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
                }
                2 -> {
                    binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar2.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar3.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar4.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
                }
                3 -> {
                    binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar2.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar3.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar4.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
                }
                4 -> {
                    binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar2.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar3.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar4.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
                }
                5 -> {
                    binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar2.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar3.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar4.setBackgroundResource(R.drawable.ic_on)
                    binding.ivStar5.setBackgroundResource(R.drawable.ic_on)
                }
                else -> {
                    binding.ivStar1.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar2.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar3.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar4.setBackgroundResource(R.drawable.ic_off)
                    binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
                }
            }
        }

    }

}