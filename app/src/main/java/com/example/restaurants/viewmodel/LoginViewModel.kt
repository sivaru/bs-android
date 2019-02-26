package com.example.restaurants.models

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.restaurants.remote.LoginService
import org.jetbrains.anko.support.v4.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(val service: LoginService) : ViewModel(){
    var username = ""
    var password = ""

    val isAuthenticated = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    init {

    }

    fun login (){
        isLoading.postValue(true)
        service.login(User(username, password)).enqueue(object : Callback<Unit> {


            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    isAuthenticated.postValue(true)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                error.postValue("error")
            }
        })
    }
}