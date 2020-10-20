package com.geekbrains.android.githubclient.mvp.model.entity.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGithubImage(
    @field:PrimaryKey @NonNull
    @field:ColumnInfo(name = "url")
    val url: String,

    @field:ColumnInfo(name = "path")
    val path: String
)