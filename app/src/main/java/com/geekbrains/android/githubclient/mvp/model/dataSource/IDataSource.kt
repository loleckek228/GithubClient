package com.geekbrains.android.githubclient.mvp.model.dataSource

import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import io.reactivex.rxjava3.core.Single

interface IDataSource {

    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepositories(url: String?): Single<List<GitHubUserRepository>>
}