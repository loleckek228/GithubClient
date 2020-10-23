package com.geekbrains.android.githubclient.mvp.model.repository.irepo

import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import io.reactivex.rxjava3.core.Single

interface IUsersRepo {
    fun getUsers(): Single<List<GithubUser>>?
}