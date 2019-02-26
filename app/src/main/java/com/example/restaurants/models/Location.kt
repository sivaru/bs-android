package com.example.restaurants.models

data class Location (val lat: Float,
                     val lng: Float,
                     val address: String,
                     val photos: ArrayList<Photo>,
                     val postalCode: Integer,
                     val province: String,
                     val city: String,
                     val country: String) {

    override fun toString() = "$postalCode $city, $province, $country"

}