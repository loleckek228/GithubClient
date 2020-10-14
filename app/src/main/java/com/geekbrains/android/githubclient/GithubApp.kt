package com.geekbrains.android.githubclient

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber
import timber.log.Timber.DebugTree

class GithubApp : Application() {
    companion object {
        lateinit var instance: GithubApp
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        Timber.plant(DebugTree())
    }

    val navigatorHolder
        get() = cicerone.navigatorHolder

    val router
        get() = cicerone.router
}