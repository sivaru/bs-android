package com.example.restaurants.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Location(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val lat: Float = 0.0f,
    val lng: Float = 0.0f,
    val address: String = "",
    val photos: ArrayList<Photo> = ArrayList(),
    val postalCode: Int = 0,
    val province: String = "",
    val city: String = "",
    val country: String = ""
) {
    override fun toString() = "$postalCode $city, $province, $country"

}