package com.geekbrains.android.githubclient.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser

class UserViewModel : ViewModel() {
    private val userLoginLiveData = MutableLiveData<GithubUser>()

    fun getUserLoginLiveData(): MutableLiveData<GithubUser> {
        return userLoginLiveData
    }
}