package com.example.restaurants.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Photo (@PrimaryKey(autoGenerate = true) val id: Int,  val url: String, val name: String)