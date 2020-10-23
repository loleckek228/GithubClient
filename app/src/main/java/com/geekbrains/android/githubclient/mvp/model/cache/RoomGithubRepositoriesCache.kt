package com.geekbrains.android.githubclient.mvp.model.cache

import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubRepositoriesCache
import com.geekbrains.android.githubclient.mvp.model.dataSource.Database
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import com.geekbrains.android.githubclient.utils.mapGitHubRepositories
import com.geekbrains.android.githubclient.utils.mapRoomGitHubRepositories
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache(private val dataBase: Database) : IRoomGithubRepositoriesCache {
    override fun getRepositories(userLogin: String?): Single<List<GitHubUserRepository>> {
        return Single.fromCallable {
            mapGitHubRepositories(dataBase.repositoryDao().findByUserLogin(userLogin!!))
        }
    }

    override fun insertRepositories(
        repositories: List<GitHubUserRepository>,
        userLogin: String?
    ): Completable {
        return Completable.fromAction {
            val roomRepositories = mapRoomGitHubRepositories(repositories, userLogin)
            dataBase.repositoryDao().insert(roomRepositories)
        }
    }
}