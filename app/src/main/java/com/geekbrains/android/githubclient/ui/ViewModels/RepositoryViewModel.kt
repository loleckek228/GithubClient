package com.geekbrains.android.githubclient.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.android.githubclient.mvp.model.entity.GitHubUserRepository

class RepositoryViewModel : ViewModel() {
    private val liveData = MutableLiveData<GitHubUserRepository>()

    fun getLiveData(): MutableLiveData<GitHubUserRepository> = liveData
}