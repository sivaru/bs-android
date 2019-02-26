package com.example.restaurants.models

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.restaurants.remote.RestaurantsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantsViewModel(val service: RestaurantsService) : ViewModel() {

    val restaurants = MutableLiveData<ArrayList<Restaurant>>()
    val restaurant = MutableLiveData<Restaurant>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.postValue(true)
        service.getRestaurants().enqueue(object : Callback<ArrayList<Restaurant>> {
            override fun onFailure(call: Call<ArrayList<Restaurant>>, t: Throwable) {
                isLoading.postValue(false)
                errorMessage.postValue("An error occurred: " + t.localizedMessage)
            }

            override fun onResponse(call: Call<ArrayList<Restaurant>>, response: Response<ArrayList<Restaurant>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        restaurants.postValue(it)
                    }
                }
                isLoading.postValue(false)
            }
        })
    }

    fun getRestaurant(id: String) {
        isLoading.postValue(true)
        service.getRestaurant(id).enqueue(object: Callback<Restaurant> {
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