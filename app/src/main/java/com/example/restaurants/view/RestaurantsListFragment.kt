package com.example.restaurants.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.leonardomadrigal.androidbasics.view.RestaurantsViewModelFactory
import com.example.restaurants.R
import com.example.restaurants.models.RestaurantsAdapter
import com.example.restaurants.models.RestaurantsViewModel
import com.example.restaurants.remote.RestaurantsService
import kotlinx.android.synthetic.main.fragment_restaurants_list.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RestaurantsListFragment : Fragment() {
    private var adapter = RestaurantsAdapter {
        showId(it)
    }

    private val rViewModel by lazy {
        ViewModelProviders.of(this, RestaurantsViewModelFactory(RestaurantsService.instance))
            .get(RestaurantsViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        rViewModel.restaurants.observe(this, Observer {
            it?.let{
                restaurants -> adapter.update(restaurants)
            }
        })

        rViewModel.isLoading.observe(this, Observer {
            it?.let{
              if(it){
                  loading.visibility = View.VISIBLE
              } else {
                  loading.visibility = View.GONE
              }

            }
        })
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

        restaurantsList.adapter = adapter
        restaurantsList.layoutManager = LinearLayoutManager(context)
        restaurantsList.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
    }

    fun showId(id:String){
        Toast.makeText(getActivity(),id,Toast.LENGTH_SHORT).show();
    }

}
