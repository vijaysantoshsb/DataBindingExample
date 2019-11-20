package com.example.databindingexample.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindingexample.models.UserDetails
import com.example.databindingexample.roomdatabase.dao.UserDAO
import com.example.databindingexample.roomdatabase.dao.database.AppDatabase

class LoginViewmodel : AndroidViewModel {
    constructor(application: Application) : super(application)

    private var userName = MutableLiveData<String>()
    private var password = MutableLiveData<String>()
    private var mobileNumber = MutableLiveData<String>()

    private var loginDetails = MutableLiveData<UserDetails>()
    private var appDatabase = AppDatabase
    private var userDAO: UserDAO? = null

    init {
        userDAO = appDatabase.getDatabase(getApplication()).userDao()
    }

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
            UserDetails(
                username = userName.value!!,
                password = password.value!!,
                mobile = mobileNumber.value!!
            )
        userDAO?.insertUser(loginDetails.value!!)

    }

    fun getLoginDetail(): MutableLiveData<UserDetails> {
        return loginDetails
    }

    fun getAllUsers(username: String): LiveData<List<UserDetails>>? {
        return userDAO?.getUsers(username)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("LoginViewmodel", "onCleared Called")
    }
}