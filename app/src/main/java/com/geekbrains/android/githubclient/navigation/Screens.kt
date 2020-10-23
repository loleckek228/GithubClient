package com.geekbrains.android.githubclient.navigation

import androidx.fragment.app.Fragment
import com.geekbrains.android.githubclient.ui.fragment.RepositoryFragment
import com.geekbrains.android.githubclient.ui.fragment.UserFragment
import com.geekbrains.android.githubclient.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment()
    }

    class UserScreen : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance()
    }

    class RepositoryScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? = RepositoryFragment.newInstance()
    }
}