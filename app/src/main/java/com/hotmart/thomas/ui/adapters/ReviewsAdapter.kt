package com.hotmart.thomas.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.hotmart.domain.models.presentation.Review
import com.hotmart.thomas.R
import com.hotmart.thomas.databinding.ReviewsItemAdapterBinding
import java.util.*
import kotlin.math.roundToInt


class ReviewsAdapter(
    private val context: Context
): RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {

    var reviews = listOf<Review>()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewsAdapter.ReviewsViewHolder {
        val binding = ReviewsItemAdapterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsAdapter.ReviewsViewHolder, position: Int) {
        val item = reviews[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = reviews.size

    /** ViewHolder **/

    inner class ReviewsViewHolder(
        private val binding: ReviewsItemAdapterBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private val colors = listOf(
            Color.RED, Color.BLUE, Color.GRAY, Color.GREEN, Color.YELLOW, Color.CYAN, Color.BLACK
        )

        @SuppressLint("SetTextI18n")
        fun bind(item: Review) {
            binding.tvTitle.text = item.title
            binding.tvComment.text = item.comment
            binding.tvNameAddress.text = "${item.name}, ${item.address}"
            binding.ivAvatar.setImageDrawable(generateDrawable(item))
            when (item.value.roundToInt()) {
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

        private fun generateDrawable(item: Review): TextDrawable? {
            val color = colors[Random().nextInt(colors.size)]
            val words = item.name.split(" ")
            val letters: String = if (words.size > 1)
                "${words[0].first()}${words[1].first()}".toUpperCase()
            else
                words[0].first().toUpperCase().toString()
            return TextDrawable.builder()
                .buildRound(letters, color)
        }

    }


}