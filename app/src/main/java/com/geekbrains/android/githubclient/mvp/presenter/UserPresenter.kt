package com.geekbrains.android.githubclient.mvp.presenter

import com.geekbrains.android.githubclient.mvp.view.UserView
import com.geekbrains.android.githubclient.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val router: Router) : MvpPresenter<UserView>() {

    fun showUserInfo(userLogin: String) {
        viewState.showUserLogin(userLogin)
    }

    fun backPressed(): Boolean {
        router.replaceScreen(Screens.UsersScreen())
        return true
    }
}