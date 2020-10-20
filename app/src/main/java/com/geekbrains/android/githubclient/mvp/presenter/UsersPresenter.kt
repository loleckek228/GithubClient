package com.geekbrains.android.githubclient.mvp.presenter

import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUsersRepo
import com.geekbrains.android.githubclient.mvp.presenter.list.IUserListPresenter
import com.geekbrains.android.githubclient.mvp.view.UsersView
import com.geekbrains.android.githubclient.mvp.view.itemsView.UserItemView
import com.geekbrains.android.githubclient.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber

class UsersPresenter(
    private val router: Router,
    private val scheduler: Scheduler,
    private val usersRepo: IUsersRepo,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users =
            mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]

            view.setLogin(user.login)
            view.loadAvatar(user.avatar_url)
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
        compositeDisposable.add(
            usersRepo.getUsers()
                ?.observeOn(scheduler)
                ?.subscribe({ t ->
                    val users = mutableListOf<GithubUser>()
                    users.addAll(t)

                    usersListPresenter.users.addAll(users)

                    viewState.updateList()
                }, { e ->
                    Timber.w(e)
                })
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}