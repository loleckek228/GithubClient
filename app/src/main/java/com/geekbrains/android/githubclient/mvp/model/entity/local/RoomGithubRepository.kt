package com.geekbrains.android.githubclient.mvp.model.entity.local

import androidx.annotation.NonNull
import androidx.room.*

@Entity
class RoomGithubRepository(
    @field:PrimaryKey @NonNull
    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "forks")
    val forks: Int,

    @field:ColumnInfo(name = "isPrivate")
    val isPrivate: String,

    @field:ColumnInfo(name = "userLogin")
    val userLogin: String,
)

