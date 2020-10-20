package com.geekbrains.android.githubclient.mvp.model.dataSource

import com.geekbrains.android.githubclient.mvp.model.entity.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

class DataSourceRemote(private val remoteProvider: RetrofitImpl = RetrofitImpl()) :
    IDataSource {

    override fun getUsers(): Single<List<GithubUser>> =
        remoteProvider.getUsers()

    override fun getUserRepositories(url: String?): Single<List<GitHubUserRepository>> =
        remoteProvider.getUserRepositories(url)
}