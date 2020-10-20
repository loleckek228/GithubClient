package com.geekbrains.android.githubclient.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser

class UserViewModel : ViewModel() {
    private val liveData = MutableLiveData<GithubUser>()

    fun getLiveData(): MutableLiveData<GithubUser> {
        return liveData
    }
}