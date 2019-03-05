package com.example.restaurants.utility

import android.arch.persistence.room.TypeConverter
import com.example.restaurants.models.Location
import com.example.restaurants.models.Photo
import com.example.restaurants.models.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toLocation(location: String): Location = Gson().fromJson(location, Location::class.java)

        @TypeConverter
        @JvmStatic
        fun fromLocation(location: Location): String = Gson().toJson(location)

        @TypeConverter
        @JvmStatic
        fun toReview(review: String): Review = Gson().fromJson(review, Review::class.java)

        @TypeConverter
        @JvmStatic
        fun fromReview(review: Review): String = Gson().toJson(review)

        @TypeConverter
        @JvmStatic
        fun fromReviews(review: List<Review>): String = Gson().toJson(review)

        @TypeConverter
        @JvmStatic
        fun toReviews(reviews: String): List<Review>  {
            val type : Type = object : TypeToken<ArrayList<Review>>() {}.type
            return Gson().fromJson(reviews, type)
        }

        @TypeConverter
        @JvmStatic
        fun fromReviewList(reviews: ArrayList<Review>): String = Gson().toJson(reviews)
        //hacer los demas

        @TypeConverter
        @JvmStatic
        fun toPhotoList(reviews: String): ArrayList<Photo>  {
            val type : Type = object : TypeToken<ArrayList<Photo>>() {}.type
            return Gson().fromJson(reviews, type)
        }

        @TypeConverter
        @JvmStatic
        fun fromPhotoList(reviews: ArrayList<Photo>): String = Gson().toJson(reviews)
    }
}