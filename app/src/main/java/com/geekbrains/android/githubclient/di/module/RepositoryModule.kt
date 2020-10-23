package com.geekbrains.android.githubclient.di.module

import com.geekbrains.android.githubclient.mvp.model.api.IDataSource
import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubRepositoriesCache
import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubUsersCache
import com.geekbrains.android.githubclient.mvp.model.network.INetworkStatus
import com.geekbrains.android.githubclient.mvp.model.repository.UserRepo
import com.geekbrains.android.githubclient.mvp.model.repository.UsersRepo
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUserRepo
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        usersCache: IRoomGithubUsersCache
    ): IUsersRepo =
        UsersRepo(api, networkStatus, usersCache)

    @Singleton
    @Provides
    fun userRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        usersCache: IRoomGithubRepositoriesCache
    ): IUserRepo =
        UserRepo(api, networkStatus, usersCache)
}