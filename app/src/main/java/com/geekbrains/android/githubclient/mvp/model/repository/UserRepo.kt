package com.geekbrains.android.githubclient.mvp.model.repository

import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubRepositoriesCache
import com.geekbrains.android.githubclient.mvp.model.dataSource.IDataSource
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.network.INetworkStatus
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUserRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val repositoriesCache: IRoomGithubRepositoriesCache
) : IUserRepo {
    override fun getUserRepository(
        url: String?,
        userlogin: String?
    ): Single<List<GitHubUserRepository>> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUserRepositories(url)
                    .flatMap { repositories ->
                        repositoriesCache.insertRepositories(repositories, userlogin)
                            .toSingleDefault(repositories)
                    }
            } else {
                repositoriesCache.getRepositories(userlogin)
            }
        }.subscribeOn(Schedulers.io())
    }
}