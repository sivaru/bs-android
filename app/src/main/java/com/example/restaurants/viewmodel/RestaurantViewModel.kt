package com.example.restaurants.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.restaurants.models.Restaurant
import com.example.restaurants.remote.RestaurantsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantViewModel(service: RestaurantsService, var restaurantId : String ) : ViewModel() {

    val restaurant = MutableLiveData<Restaurant>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.postValue(true)


        service.getRestaurant(this.restaurantId ).enqueue(object : Callback<Restaurant> {
            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                isLoading.postValue(false)
                errorMessage.postValue("An error occurred: " + t.localizedMessage)
            }

            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        restaurant.postValue(it)
                    }
                }
                isLoading.postValue(false)
            }
        })

    }

}