package com.geekbrains.android.githubclient.di.module

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneModule {
    var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone() = cicerone

    @Singleton
    @Provides
    fun navigatorHolder() = cicerone.navigatorHolder

    @Singleton
    @Provides
    fun router() = cicerone.router
}