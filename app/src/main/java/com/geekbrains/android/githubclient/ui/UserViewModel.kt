package com.geekbrains.android.githubclient.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val userLoginLiveData = MutableLiveData<String>()

    fun getUserLoginLiveData(): MutableLiveData<String> {
        return userLoginLiveData
    }
}