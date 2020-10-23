package com.geekbrains.android.githubclient.mvp.presenter

import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.view.RepositoryView
import moxy.MvpPresenter

class RepositoryPresenter : MvpPresenter<RepositoryView>() {
    fun showRepositoryInfo(repository: GitHubUserRepository?) {
        val name = repository?.name
        val isPrivate = repository?.private
        val forks = repository?.forks

        viewState.setName(name)
        viewState.isPrivate(isPrivate)
        viewState.setForks(forks)
    }
}