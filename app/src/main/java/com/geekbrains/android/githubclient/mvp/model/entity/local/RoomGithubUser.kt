package com.geekbrains.android.githubclient.mvp.model.entity.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity
data class RoomGithubUser(
    @field:PrimaryKey @NonNull

    @field:ColumnInfo(name = "login")
    val login: String,

    @field:ColumnInfo(name = "avatar_url")
    val avatar_url: String,

    @field:ColumnInfo(name = "repos_url")
    val repos_url: String,
)