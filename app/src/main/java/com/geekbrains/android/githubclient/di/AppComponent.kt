package com.geekbrains.android.githubclient.di

import com.geekbrains.android.githubclient.di.module.*
import com.geekbrains.android.githubclient.mvp.presenter.MainPresenter
import com.geekbrains.android.githubclient.mvp.presenter.UserPresenter
import com.geekbrains.android.githubclient.mvp.presenter.UsersPresenter
import com.geekbrains.android.githubclient.ui.activity.MainActivity
import com.geekbrains.android.githubclient.ui.adapters.UsersRVAdapter
import com.geekbrains.android.githubclient.ui.fragment.UserFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        RepositoryModule::class,
        ImageLoaderModule::class,
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
    fun inject(userFragment: UserFragment)
}