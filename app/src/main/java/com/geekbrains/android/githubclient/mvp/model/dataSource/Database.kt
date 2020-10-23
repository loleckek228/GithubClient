package com.geekbrains.android.githubclient.mvp.model.dataSource

import androidx.room.Room
import androidx.room.RoomDatabase
import com.geekbrains.android.githubclient.GithubApp
import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubImage
import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubRepository
import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubUser
import com.geekbrains.android.githubclient.mvp.model.entity.local.dao.ImageDao
import com.geekbrains.android.githubclient.mvp.model.entity.local.dao.RepositoryDao
import com.geekbrains.android.githubclient.mvp.model.entity.local.dao.UserDao

@androidx.room.Database(
    entities = arrayOf(
        RoomGithubUser::class,
        RoomGithubRepository::class,
        RoomGithubImage::class
    ),
    version = 1,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao
    abstract fun imageDao(): ImageDao

    companion object {
        const val DB_NAME = "database.db"

        @Volatile
        private var instance: Database? = null

        fun getInstance(): Database? {
            if (instance == null) {
                create()
            }

            return instance
        }

        private fun create() {
            val localRef: Database? = instance

            if (localRef == null) {
                synchronized(Database::class.java) {
                    instance = localRef

                    if (localRef == null) {
                        instance = localRef
                        instance = Room.databaseBuilder(
                            GithubApp.instance.applicationContext,
                            Database::class.java,
                            DB_NAME
                        )
                            .fallbackToDestructiveMigrationOnDowngrade()
                            .build()
                    }
                }
            }
        }
    }
}