package com.geekbrains.android.githubclient.mvp.presenter

import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUserRepo
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUsersRepo
import com.geekbrains.android.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.geekbrains.android.githubclient.mvp.view.UserView
import com.geekbrains.android.githubclient.mvp.view.itemsView.RepositoryItemView
import com.geekbrains.android.githubclient.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val router: Router,
    private val scheduler: Scheduler,
    private val usersRepo: IUserRepo,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MvpPresenter<UserView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {
        val repositories =
            mutableListOf<GitHubUserRepository>()

        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]

            view.setRepository(repository.name)
        }

        override fun getCount(): Int = repositories.size
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        repositoriesListPresenter.itemClickListener = {
            router.navigateTo(Screens.RepositoryScreen())

            val repository = repositoriesListPresenter.repositories[it.pos]
            viewState.sendRepository(repository)
        }
    }

    val repositoriesListPresenter = RepositoryListPresenter()

    private fun loadData(user: GithubUser) {
        compositeDisposable.add(
            usersRepo.getUserRepository(user.repos_url, user.login)
                .observeOn(scheduler)
                .subscribe { t ->
                    val repositories = mutableListOf<GitHubUserRepository>()
                    repositories.addAll(t)

                    repositoriesListPresenter.repositories.addAll(repositories)

                    viewState.updateList()
                    viewState.setLogin(user.login)
                    viewState.loadImage(user.avatar_url)
                })
    }

    fun showUserInfo(user: GithubUser) {
        loadData(user)
    }

    fun backPressed(): Boolean {
        router.replaceScreen(Screens.UsersScreen())
        return true
    }
}