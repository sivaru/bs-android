package com.example.restaurants.remote

import com.example.restaurants.models.Restaurant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestaurantsService {
    companion object {
        val instance : RestaurantsService by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okhttpClientBuilder = OkHttpClient.Builder()
            okhttpClientBuilder.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://restaurantsreviews-bstn.firebaseapp.com/api/v1/")
                .client(okhttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RestaurantsService::class.java)
        }
    }

    @GET("restaurants")
    fun getRestaurants(): Call<ArrayList<Restaurant>>
}