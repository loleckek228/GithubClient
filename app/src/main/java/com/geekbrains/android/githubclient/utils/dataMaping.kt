package com.geekbrains.android.githubclient.utils

import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubRepository
import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubUser
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser

fun mapRoomGitHubUsers(users: List<GithubUser>): List<RoomGithubUser> {
    val newUsers = mutableListOf<RoomGithubUser>()

    users.map {
        val user = RoomGithubUser(
            it.login!!,
            it.avatar_url!!,
            it.repos_url!!
        )

        newUsers.add(user)
    }

    return newUsers
}

fun mapGitHubUsers(users: List<RoomGithubUser>): List<GithubUser> {
    val newUsers = mutableListOf<GithubUser>()

    users.map {
        val user = GithubUser(
            it.login,
            it.avatar_url,
            it.repos_url
        )

        newUsers.add(user)
    }

    return newUsers
}

fun mapRoomGitHubRepositories(
    repositories: List<GitHubUserRepository>,
    userLogin: String?
): List<RoomGithubRepository> {
    val roomGithubRepositories = mutableListOf<RoomGithubRepository>()

    repositories.map {
        val repository = RoomGithubRepository(
            it.name!!,
            it.forks!!,
            it.private.toString(),
            userLogin!!,
        )

        roomGithubRepositories.add(repository)
    }

    return roomGithubRepositories
}

fun mapGitHubRepositories(users: List<RoomGithubRepository>): List<GitHubUserRepository> {
    val gitHubUserRepositories = mutableListOf<GitHubUserRepository>()

    users.map {
        val repository = GitHubUserRepository(
            it.name,
            it.forks,
            it.isPrivate.toBoolean()
        )

        gitHubUserRepositories.add(repository)
    }

    return gitHubUserRepositories
}