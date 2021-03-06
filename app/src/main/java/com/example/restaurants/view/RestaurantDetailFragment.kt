package com.example.restaurants.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.restaurants.R
import com.example.restaurants.application.RestaurantDatabase
import com.example.restaurants.models.Restaurant
import com.example.restaurants.models.RestaurantsViewModel
import com.example.restaurants.models.ReviewsAdapter
import com.example.restaurants.remote.RestaurantsService
import com.example.restaurants.viewmodel.RestaurantViewModel
import com.example.restaurants.viewmodel.RestaurantViewModelFactory
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*


class RestaurantDetailFragment : Fragment() {
    private val rViewModel by lazy {
        ViewModelProviders.of(this, RestaurantViewModelFactory(RestaurantsService.instance, this.id!!))
            .get(RestaurantViewModel::class.java)
    }

    private var id = ""
    private var isLoading = false
    private var adapter = ReviewsAdapter()

    override fun onStart() {
        super.onStart()
        arguments?.let {
            id = it.getString("id")
        }
        restaurant_reviews.adapter = adapter
        restaurant_reviews.layoutManager = LinearLayoutManager(context)
        restaurant_reviews.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        rViewModel?.restaurant?.observe(this, Observer {
            it?.let {
                initialize(it)
            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    fun initialize(restaurant: Restaurant) {
        val options = RequestOptions()
            .placeholder(R.drawable.question_mark)
            .fallback(ColorDrawable(Color.GRAY))

        context?.let {
            Glide.with(it)
                .load(restaurant.location.photos[0].url)
                .apply(options)
                .into(restaurant_picture)
        }
        restaurant_title.text = restaurant.name
        restaurant_rating.rating = restaurant.overallRating
        restaurant_rating.setIsIndicator(true)
        if (restaurant.reviews.isNotEmpty()) {
            adapter.update(restaurant.reviews)
        } else {
            restaurant_rating.visibility = View.GONE
            restaurant_no_review.visibility = View.VISIBLE
        }
    }
}
