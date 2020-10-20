package com.geekbrains.android.githubclient.mvp.model.cache

import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubUsersCache
import com.geekbrains.android.githubclient.mvp.model.dataSource.local.Database
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import com.geekbrains.android.githubclient.utils.mapGitHubUsers
import com.geekbrains.android.githubclient.utils.mapRoomGitHubUsers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(private val dataBase: Database) : IRoomGithubUsersCache {
    override fun getUsers(): Single<List<GithubUser>> {
        return Single.fromCallable {
            mapGitHubUsers(dataBase.userDao().getUsers())
        }/*.subscribeOn(Schedulers.io())*/
    }

    override fun insertUsers(users: List<GithubUser>): Single<List<GithubUser>> {
        return Single.fromCallable {
            val roomUsers = mapRoomGitHubUsers(users)
            dataBase.userDao().insert(roomUsers)
            users
        }
    }
}