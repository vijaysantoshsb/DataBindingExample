package com.example.databindingexample.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.databindingexample.R
import com.example.databindingexample.viewmodel.LoginViewmodel
import kotlinx.android.synthetic.main.login_fragment_layout.*
import kotlinx.android.synthetic.main.register_fragment.*
import kotlinx.android.synthetic.main.register_fragment.btn_submit

class LoginFragment : Fragment() {

    private var viewmodel: LoginViewmodel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.login_fragment_layout, container, false)
        viewmodel = activity?.run {
            ViewModelProviders.of(this)[LoginViewmodel::class.java]
        } ?: throw Exception("Invalid Activity")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel?.getLoginDetail()?.observe(this, Observer {
            Log.i("LoginFragment", it.username)
        })
        viewmodel?.getAllUsers()?.observe(this, Observer {
            Log.i("userdata", it[0].username)
        })
    }
}