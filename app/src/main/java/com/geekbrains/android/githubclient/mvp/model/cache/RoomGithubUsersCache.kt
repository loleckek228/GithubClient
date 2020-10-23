package com.geekbrains.android.githubclient.mvp.model.cache

import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubUsersCache
import com.geekbrains.android.githubclient.mvp.model.dataSource.Database
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import com.geekbrains.android.githubclient.utils.mapGitHubUsers
import com.geekbrains.android.githubclient.utils.mapRoomGitHubUsers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(private val dataBase: Database) : IRoomGithubUsersCache {
    override fun getUsers(): Single<List<GithubUser>> {
        return Single.fromCallable {
            mapGitHubUsers(dataBase.userDao().getUsers())
        }
    }

    override fun insertUsers(users: List<GithubUser>): Completable {
        return Completable.fromAction() {
            val roomUsers = mapRoomGitHubUsers(users)
            dataBase.userDao().insert(roomUsers)
        }
    }
}