package com.geekbrains.android.githubclient.mvp.model.Repository

import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser

class GithubUsersRepo {
    private val repositories =
        listOf(
            GithubUser("login1"),
            GithubUser("login2"),
            GithubUser("login3"),
            GithubUser("login4"),
            GithubUser("login5")
        )

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}