package com.example.databindingexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.databindingexample.R
import com.example.databindingexample.databinding.RegisterFragmentBinding
import com.example.databindingexample.viewmodel.LoginViewmodel
import kotlinx.android.synthetic.main.register_fragment.btn_submit
import kotlinx.android.synthetic.main.register_fragment.edt_username

class RegisterFragment : Fragment() {

    var viewmodel: LoginViewmodel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewmodel = activity?.run {
            ViewModelProviders.of(this)[LoginViewmodel::class.java]
        } ?: throw Exception("Invalid Activity")

        val databinding =
            DataBindingUtil.inflate<RegisterFragmentBinding>(inflater, R.layout.register_fragment, container, false)
        databinding.lifecycleOwner = this
        databinding.userdetails = viewmodel
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_submit.setOnClickListener {
            insertUser(it)
        }
    }

    private fun insertUser(view: View) {
        viewmodel?.getAllUsers(edt_username.text.toString())?.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                for (userList in it) {
                    if (userList.username.equals(edt_username.text.toString())) {
                        Toast.makeText(requireActivity(), "User found", Toast.LENGTH_SHORT).show()
                    } else {
                        viewmodel?.setLoginDetails()
                        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
                    }
                }
            } else {
                viewmodel?.setLoginDetails()
                Navigation.findNavController(btn_submit).navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
        )
    }
}