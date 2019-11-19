package com.example.databindingexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databindingexample.models.UserDetails

class LoginViewmodel : ViewModel() {

    private var userName = MutableLiveData<String>()
    private var password = MutableLiveData<String>()
    private var mobileNumber = MutableLiveData<String>()

    private var loginDetails = MutableLiveData<UserDetails>()


    fun getUserName(): MutableLiveData<String> {
        return userName
    }

    fun getPassword(): MutableLiveData<String> {
        return password
    }

    fun getMobileNumber(): MutableLiveData<String> {
        return mobileNumber
    }

    fun setLoginDetails() {
        loginDetails.value =
            UserDetails(username = userName.value!!, password = password.value!!, mobile = mobileNumber.value!!)
    }

    fun getLoginDetail(): MutableLiveData<UserDetails> {
        return loginDetails
    }

}