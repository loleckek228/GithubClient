package com.geekbrains.android.githubclient.mvp.view

import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun init()
    fun setLogin(login: String?)
    fun loadImage(url: String?)
    fun updateList()
    fun sendRepository(repository: GitHubUserRepository?)
}