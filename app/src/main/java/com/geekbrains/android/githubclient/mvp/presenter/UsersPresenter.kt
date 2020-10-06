package com.geekbrains.android.githubclient.mvp.presenter

import com.geekbrains.android.githubclient.mvp.model.Repository.GithubUsersRepo
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser
import com.geekbrains.android.githubclient.mvp.presenter.list.IUserListPresenter
import com.geekbrains.android.githubclient.mvp.view.UserItemView
import com.geekbrains.android.githubclient.mvp.view.UsersView
import com.geekbrains.android.githubclient.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
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
            router.navigateTo(Screens.UserScreen())

            val user = usersListPresenter.users[it.pos]
            viewState.sendUser(user)
        }
    }

    private fun loadData() {

        usersRepo.getUsers().subscribe { t ->
            val users = mutableListOf<GithubUser>()
            users.add(t)

            usersListPresenter.users.addAll(users)
        }

        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}