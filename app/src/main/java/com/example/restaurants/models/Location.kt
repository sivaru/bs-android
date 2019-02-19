package com.example.restaurants.models

data class Location (val lat: Float,
                     val lng: Float,
                     val address: String,
                     val photos: ArrayList<String>,
                     val postalCode: Integer,
                     val province: String,
                     val city: String,
                     val country: String)