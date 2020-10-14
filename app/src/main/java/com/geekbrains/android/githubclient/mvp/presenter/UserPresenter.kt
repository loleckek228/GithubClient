package com.geekbrains.android.githubclient.mvp.presenter

import com.geekbrains.android.githubclient.mvp.model.dataSource.DataSourceRemote
import com.geekbrains.android.githubclient.mvp.model.entity.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser
import com.geekbrains.android.githubclient.mvp.model.repository.UsersRepo
import com.geekbrains.android.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.geekbrains.android.githubclient.mvp.view.itemsView.RepositoryItemView
import com.geekbrains.android.githubclient.mvp.view.UserView
import com.geekbrains.android.githubclient.navigation.Screens
import com.geekbrains.android.githubclient.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val router: Router,
    private val repository: UsersRepo = UsersRepo(
        DataSourceRemote()
    ),
    private val schedulersProvider: SchedulerProvider = SchedulerProvider(),
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
            repository.getUserRepository(user.repos_url)
                .observeOn(schedulersProvider.ui())
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