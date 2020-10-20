package com.geekbrains.android.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView : MvpView {
    fun setName(name: String?)
    fun isPrivate(isPrivate: Boolean?)
    fun setForks(count: Int?)
}