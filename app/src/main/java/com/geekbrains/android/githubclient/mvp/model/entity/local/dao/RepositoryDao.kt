package com.geekbrains.android.githubclient.mvp.model.entity.local.dao

import androidx.room.*
import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubRepository

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubRepository>)

    @Update
    fun update(user: RoomGithubRepository)

    @Update
    fun update(vararg users: RoomGithubRepository)

    @Update
    fun update(users: List<RoomGithubRepository>)

    @Delete
    fun delete(user: RoomGithubRepository)

    @Delete
    fun delete(vararg users: RoomGithubRepository)

    @Delete
    fun delete(users: List<RoomGithubRepository>)

    @Query("SELECT * FROM RoomGithubRepository")
    fun getRepositories(): List<RoomGithubRepository>

    @Query("SELECT * FROM RoomGithubRepository WHERE userLogin =:userLogin")
    fun findByUserLogin(userLogin: String): List<RoomGithubRepository>
}