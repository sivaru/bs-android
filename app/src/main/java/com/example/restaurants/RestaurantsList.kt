package com.example.restaurants

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurants.models.Restaurant
import com.example.restaurants.models.RestaurantsAdapter
import com.example.restaurants.remote.RestaurantsService
import kotlinx.android.synthetic.main.fragment_restaurants_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RestaurantsList : Fragment() {
    private var adapter = RestaurantsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragment = inflater.inflate(R.layout.fragment_restaurants_list, container, false)


        return fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getRestaurants()
        restaurantsList.adapter = adapter
        restaurantsList.layoutManager = LinearLayoutManager(context)
        restaurantsList.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
    }

    fun getRestaurants(){
          RestaurantsService.instance.getRestaurants().enqueue(object : Callback<ArrayList<Restaurant>> {
              override fun onResponse(call: Call<ArrayList<Restaurant>>, response: Response<ArrayList<Restaurant>>) {
                      when (response.isSuccessful) {
                          true -> if (response.isSuccessful) {
                              response.body()?.let {
                                  println("ya llego la madre")
                                  adapter.update(it)
                              }
                          }
                          false -> println("Failure")
                      }
                                }

              override fun onFailure(call: Call<ArrayList<Restaurant>>, t: Throwable) {

                      println("error")

              }
          })

    }

}
