package com.example.restaurants.models

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.restaurants.remote.LoginService

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val service: LoginService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(service) as T
    }
}

