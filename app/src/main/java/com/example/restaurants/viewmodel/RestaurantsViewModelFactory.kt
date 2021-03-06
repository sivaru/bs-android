package com.example.restaurants.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.restaurants.application.RestaurantDatabase
import com.example.restaurants.remote.RestaurantsService
import com.example.restaurants.models.RestaurantsViewModel


@Suppress("UNCHECKED_CAST")
class RestaurantsViewModelFactory(private val service: RestaurantsService, private val db: RestaurantDatabase?) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestaurantsViewModel(service, db) as T
    }
}

