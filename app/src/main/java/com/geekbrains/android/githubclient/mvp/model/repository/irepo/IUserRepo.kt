package com.geekbrains.android.githubclient.mvp.model.repository.irepo

import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import io.reactivex.rxjava3.core.Single

interface IUserRepo {
    fun getUserRepository(url: String?, login: String?): Single<List<GitHubUserRepository>>
}