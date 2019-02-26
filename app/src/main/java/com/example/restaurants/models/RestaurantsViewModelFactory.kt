package com.example.leonardomadrigal.androidbasics.view

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.restaurants.remote.RestaurantsService
import com.example.restaurants.models.RestaurantsViewModel


@Suppress("UNCHECKED_CAST")
class RestaurantsViewModelFactory(private val service: RestaurantsService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestaurantsViewModel(service) as T
    }
}

