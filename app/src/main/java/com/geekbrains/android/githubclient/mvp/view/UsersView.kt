package com.geekbrains.android.githubclient.mvp.view

import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun init()
    fun updateList()
    fun sendUser(user: GithubUser)
}
