package com.geekbrains.android.githubclient.mvp.model.cache.icache

import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubRepository
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomGithubRepositoriesCache {
    fun getRepositories(userLogin: String?): Single<List<GitHubUserRepository>>

    fun insertRepositories(
        repositories: List<GitHubUserRepository>,
        userLogin: String?
    ): Completable
}