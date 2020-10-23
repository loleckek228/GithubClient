package com.geekbrains.android.githubclient

import android.app.Application
import com.geekbrains.android.githubclient.di.AppComponent
import com.geekbrains.android.githubclient.di.DaggerAppComponent
import com.geekbrains.android.githubclient.di.module.AppModule
import timber.log.Timber
import timber.log.Timber.DebugTree

class GithubApp : Application() {
    companion object {
        lateinit var instance: GithubApp
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        Timber.plant(DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}