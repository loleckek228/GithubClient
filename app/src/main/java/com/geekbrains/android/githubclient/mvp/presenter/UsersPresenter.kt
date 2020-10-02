package com.geekbrains.android.githubclient.mvp.presenter

import com.geekbrains.android.githubclient.mvp.model.Repository.GithubUsersRepo
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser
import com.geekbrains.android.githubclient.mvp.presenter.list.IUserListPresenter
import com.geekbrains.android.githubclient.mvp.view.UserItemView
import com.geekbrains.android.githubclient.mvp.view.UsersView
import com.geekbrains.android.githubclient.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users =
            mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        loadData()

        usersListPresenter.itemClickListener = {
            router.replaceScreen(Screens.UserScreen())

            val userLogin = usersListPresenter.users[it.pos].login
            viewState.sendUserLogin(userLogin)
        }
    }

    fun loadData() {
        val users =
            usersRepo.getUsers()
        usersListPresenter.users.addAll(users)

        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}