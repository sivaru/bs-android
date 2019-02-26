package com.example.restaurants

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurants.view.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            val fragmentManager = supportFragmentManager
                .beginTransaction()
                .add(R.id.frame, LoginFragment())
                .commit()
        }

    }
}
