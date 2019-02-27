package com.example.restaurants.models

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.restaurants.R
import kotlinx.android.synthetic.main.restaurant_item.view.*


class RestaurantsAdapter(private var items: List<Restaurant> = emptyList(), private val onClick: (String) -> Unit) : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, index: Int): RestaurantsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: RestaurantsAdapter.ViewHolder, index: Int) {
        viewHolder.bind(items[index], onClick)
    }

    fun update(restaurants: List<Restaurant>) {
        items = restaurants
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: Restaurant, onClick: (String) -> Unit) {
            itemView.setOnClickListener {
                onClick(restaurant.id)
            }
            itemView.name.text = restaurant.name
            restaurant.location.photos.firstOrNull()?.let {
                val options = RequestOptions()
                    .placeholder(R.drawable.question_mark)
                    .fallback(ColorDrawable(Color.GRAY))

                Glide.with(itemView.context)
                    .load(it.url)
                    .apply(options)
                    .into(itemView.picture)

            }
            itemView.location.text = restaurant.location.address
        }
    }
}