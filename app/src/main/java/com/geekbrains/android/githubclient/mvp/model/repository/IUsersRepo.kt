package com.geekbrains.android.githubclient.mvp.model.repository

import com.geekbrains.android.githubclient.mvp.model.entity.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepository(url: String?): Single<List<GitHubUserRepository>>
}