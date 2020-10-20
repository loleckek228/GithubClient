package com.geekbrains.android.githubclient.mvp.presenter.list

import com.geekbrains.android.githubclient.mvp.view.itemsView.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
