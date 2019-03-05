package com.example.restaurants.models.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

@Dao
interface BaseDao<Item> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<Item>)

    @Delete
    fun delete(obj: Item)

}