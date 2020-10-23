package com.geekbrains.android.githubclient.mvp.model.api

import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getUserRepository(@Url url: String?): Single<List<GitHubUserRepository>>
}