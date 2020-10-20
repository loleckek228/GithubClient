package com.geekbrains.android.githubclient.mvp.view.itemsView

interface UserItemView : IItemView {

    fun setLogin(text: String?)

    fun loadAvatar(url: String?)
}
