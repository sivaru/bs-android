package com.example.restaurants

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurants.models.LoginViewModel
import com.example.restaurants.models.LoginViewModelFactory
import com.example.restaurants.models.User
import com.example.restaurants.remote.LoginService
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.support.v4.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Login : Fragment() {


    private val lViewModel by lazy {
        ViewModelProviders.of(this, LoginViewModelFactory(LoginService.instance))
            .get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        button3.setOnClickListener{
           lViewModel.login()
        }

        email.afterTextChanged {
            lViewModel.username = it
        }

        password.afterTextChanged {
            lViewModel.password = it
        }

        lViewModel.isAuthenticated.observe(this, Observer {
            it?.let{
                   if(it) {
                       onSuccess()
                   }
            }
        })

    }

    fun onSuccess() {
        showMessage ("Login Successful")
        val fragmentManager = getFragmentManager()
            ?.beginTransaction()
            ?.replace(R.id.frame, RestaurantsList())
            ?.commit()
    }

    fun showMessage(message: String) {
        println(message)
    }
}
