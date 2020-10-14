package com.geekbrains.android.githubclient.mvp.model.repository

import com.geekbrains.android.githubclient.mvp.model.dataSource.IDataSource
import com.geekbrains.android.githubclient.mvp.model.entity.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser
import com.geekbrains.android.githubclient.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Single

class UsersRepo(
    private val dataSource: IDataSource,
) : IUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        dataSource.getUsers()

    override fun getUserRepository(url: String?): Single<List<GitHubUserRepository>> =
        dataSource.getUserRepositories(url)
}