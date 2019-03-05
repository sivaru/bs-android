package com.example.restaurants.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.restaurants.application.RestaurantDatabase
import com.example.restaurants.remote.RestaurantsService


@Suppress("UNCHECKED_CAST")
class RestaurantViewModelFactory(private val service: RestaurantsService, private val id: String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestaurantViewModel(service, id) as T
    }
}