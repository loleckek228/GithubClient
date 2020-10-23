package com.geekbrains.android.githubclient.mvp.model.entity.local.dao

import androidx.room.*
import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubImage
import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubRepository

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: RoomGithubImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg images: RoomGithubImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<RoomGithubImage>)

    @Update
    fun update(image: RoomGithubImage)

    @Update
    fun update(vararg images: RoomGithubImage)

    @Update
    fun update(images: List<RoomGithubImage>)

    @Delete
    fun delete(image: RoomGithubImage)

    @Delete
    fun delete(vararg images: RoomGithubImage)

    @Delete
    fun delete(images: List<RoomGithubImage>)

    @Query("SELECT * FROM RoomGithubImage")
    fun getImages(): List<RoomGithubImage>

    @Query("SELECT * FROM RoomGithubImage WHERE url =:url")
    fun findByUrl(url: String): RoomGithubImage
}