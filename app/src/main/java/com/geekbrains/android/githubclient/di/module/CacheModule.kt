package com.geekbrains.android.githubclient.di.module

import androidx.room.Room
import com.geekbrains.android.githubclient.GithubApp
import com.geekbrains.android.githubclient.mvp.model.cache.RoomGithubImageCache
import com.geekbrains.android.githubclient.mvp.model.cache.RoomGithubRepositoriesCache
import com.geekbrains.android.githubclient.mvp.model.cache.RoomGithubUsersCache
import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubImageCache
import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubRepositoriesCache
import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubUsersCache
import com.geekbrains.android.githubclient.mvp.model.dataSource.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: GithubApp): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IRoomGithubUsersCache =
        RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IRoomGithubRepositoriesCache =
        RoomGithubRepositoriesCache(database)

    @Singleton
    @Provides
    fun imageCache(database: Database): IRoomGithubImageCache =
        RoomGithubImageCache(database)
}