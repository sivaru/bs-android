package com.example.restaurants.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class Restaurant (@PrimaryKey val id: String,
                       val name: String = "",
                       val location: Location,
                       val url: String = "",
                       val reviews: List<Review>,
                        val overallRating: Float)