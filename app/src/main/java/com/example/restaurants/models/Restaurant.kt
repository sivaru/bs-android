package com.example.restaurants.models

data class Restaurant (val id: String,
                       val name: String,
                       val location: Location,
                       val url: String,
                       val overallRating: Float = 0.00f)