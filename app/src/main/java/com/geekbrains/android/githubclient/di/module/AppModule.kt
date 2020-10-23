package com.geekbrains.android.githubclient.di.module

import com.geekbrains.android.githubclient.GithubApp
import com.geekbrains.android.githubclient.mvp.presenter.MainPresenter
import com.geekbrains.android.githubclient.mvp.presenter.UserPresenter
import com.geekbrains.android.githubclient.mvp.presenter.UsersPresenter
import com.geekbrains.android.githubclient.ui.activity.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

@Module
class AppModule(val app: GithubApp) {
    @Provides
    fun app(): GithubApp = app

    @Provides
    fun mainThreadScheduler() = AndroidSchedulers.mainThread()
}