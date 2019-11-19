package com.example.databindingexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.databindingexample.R
import com.example.databindingexample.databinding.RegisterFragmentBinding
import com.example.databindingexample.viewmodel.LoginViewmodel
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : Fragment() {

    var viewmodel = LoginViewmodel()
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
            viewmodel.setLoginDetails()
            Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}