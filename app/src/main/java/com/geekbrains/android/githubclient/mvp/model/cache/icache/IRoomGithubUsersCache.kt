package com.geekbrains.android.githubclient.mvp.model.cache.icache

import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubUser
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.internal.operators.completable.CompletableAmb

interface IRoomGithubUsersCache {
    fun getUsers(): Single<List<GithubUser>>
    fun insertUsers(users: List<GithubUser>): Completable
}