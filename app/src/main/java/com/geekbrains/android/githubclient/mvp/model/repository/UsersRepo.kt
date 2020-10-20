package com.geekbrains.android.githubclient.mvp.model.repository

import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubUsersCache
import com.geekbrains.android.githubclient.mvp.model.dataSource.IDataSource
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import com.geekbrains.android.githubclient.mvp.model.network.INetworkStatus
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val usersCache: IRoomGithubUsersCache,
) : IUsersRepo {
    override fun getUsers(): Single<List<GithubUser>>? {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers()
                    .flatMap { users ->
                        //кэширование
//                        Single.fromCallable {
//                            val roomUsers = mapRoomGitHubUsers(users)
//                            dataBase.userDao().insert(roomUsers)
                        usersCache.insertUsers(users)
//                            users
//                        }
                    }
            } else {
//                Single.fromCallable {
//                    mapGitHubUsers(dataBase.userDao().getUsers())
                usersCache.getUsers()
//                }
            }
        }.subscribeOn(Schedulers.io())
    }
}