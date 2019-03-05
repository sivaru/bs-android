package com.example.restaurants.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Review (@PrimaryKey val id: String, val userName: String, val text: String, val rating: Float)