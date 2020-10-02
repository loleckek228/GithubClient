package com.geekbrains.android.githubclient.navigation

import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser
import com.geekbrains.android.githubclient.ui.fragment.UserFragment
import com.geekbrains.android.githubclient.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance()
    }
}