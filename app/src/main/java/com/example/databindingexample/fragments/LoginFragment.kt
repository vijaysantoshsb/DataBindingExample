package com.example.databindingexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.databindingexample.R
import com.example.databindingexample.models.UserDetails
import com.example.databindingexample.viewmodel.LoginViewmodel
import kotlinx.android.synthetic.main.login_fragment_layout.*


class LoginFragment : Fragment() {

    private var userList: List<UserDetails>? = null
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

        btn_submit.setOnClickListener {
            viewmodel?.getAllUsers(edt_username.text.toString())?.observe(this, Observer {
                userList = it
                if (it.get(0).username.equals(edt_username.text.toString())) {
                    if (it.get(0).password.equals(edt_password.text.toString())) {
                        Toast.makeText(requireActivity(), "Login Success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireActivity(), "Password not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "User not found", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}