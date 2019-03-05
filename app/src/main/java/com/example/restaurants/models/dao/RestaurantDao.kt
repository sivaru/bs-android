package com.example.restaurants.models.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.restaurants.models.Restaurant

@Dao
interface  RestaurantDao : BaseDao<Restaurant> {
    @Query("select * from Restaurant")
    fun getAll(): LiveData<List<Restaurant>>

    @Query("DELETE FROM Restaurant")
    fun deleteAll()
}