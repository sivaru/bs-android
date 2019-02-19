package com.example.restaurants

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurants.models.User
import com.example.restaurants.remote.LoginService
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.uiThread
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Login : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
            doLogin()
        }
    }
    fun doLogin() {
        sendLogin()
    }

    private fun sendLogin(){
        val e = email.text.toString()
        val p = password.text.toString()
        LoginService.instance.login(User(e, p)).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                uiThread {
                    when (response.isSuccessful) {
                        true -> onSuccess()
                        false -> showMessage("Failure")
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                uiThread {
                    showMessage("error")
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
