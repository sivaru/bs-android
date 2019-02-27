package com.example.restaurants.models

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurants.R
import kotlinx.android.synthetic.main.review_item.view.*


class ReviewsAdapter(private var items: List<Review> = emptyList()) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, index: Int): ReviewsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ReviewsAdapter.ViewHolder, index: Int) {
        viewHolder.bind(items[index])
    }

    fun update(reviews: List<Review>) {
        items = reviews
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(review: Review) {
            itemView.textView.text = review.text
            itemView.textView2.text = review.userName
            itemView.ratingBar.rating = review.rating
            itemView.ratingBar.setIsIndicator(true)
        }
    }
}