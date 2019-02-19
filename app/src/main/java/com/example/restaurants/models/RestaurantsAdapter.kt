package com.example.restaurants.models

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurants.models.Restaurant
import com.example.restaurants.R
import kotlinx.android.synthetic.main.restaurant_item.view.*


class RestaurantsAdapter(private var items: List<Restaurant> = emptyList()) : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, index: Int): RestaurantsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_restaurants_list, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: RestaurantsAdapter.ViewHolder, index: Int) {
        viewHolder.bind(items[index])
    }

    fun update(todos: List<Restaurant>) {
        items = todos
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: Restaurant) {
            itemView.name.text = restaurant.name
        }
    }
}