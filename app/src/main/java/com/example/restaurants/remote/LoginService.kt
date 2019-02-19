package com.example.restaurants.remote

import com.example.restaurants.models.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface  LoginService {

    companion object {
        val instance : LoginService by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okhttpClientBuilder = OkHttpClient.Builder()
            okhttpClientBuilder.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://restaurantsreviews-bstn.firebaseapp.com/api/v1/")
                .client(okhttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(LoginService::class.java)
        }
    }

    @POST("user")
    fun login(@Body user: User): Call<Unit>

}