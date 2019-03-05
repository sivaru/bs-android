package com.example.restaurants.application

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.restaurants.models.Location
import com.example.restaurants.models.Photo
import com.example.restaurants.models.Restaurant
import com.example.restaurants.models.Review
import com.example.restaurants.models.dao.RestaurantDao
import com.example.restaurants.utility.Converters


@Database(entities = [Restaurant::class, Location::class, Review::class, Photo::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class RestaurantDatabase : RoomDatabase() {
    companion object {
        private const val name = "myDb"
        private var instance: RestaurantDatabase? = null

        fun getInstance(context: Context): RestaurantDatabase? {
            if (instance == null) {
                synchronized(RestaurantDatabase::class) {
                    instance = Room.databaseBuilder(context, RestaurantDatabase::class.java, name).build()
                }
            }

            return instance
        }
    }

    abstract fun getRestaurantDao(): RestaurantDao
}