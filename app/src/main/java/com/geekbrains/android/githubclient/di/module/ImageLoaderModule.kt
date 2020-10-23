package com.geekbrains.android.githubclient.di.module

import android.widget.ImageView
import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubImageCache
import com.geekbrains.android.githubclient.mvp.model.network.INetworkStatus
import com.geekbrains.android.githubclient.mvp.view.image.GlideImageLoader
import com.geekbrains.android.githubclient.mvp.view.image.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {
    @Singleton
    @Provides
    fun imageLoader(
        imageCache: IRoomGithubImageCache,
        networkStatus: INetworkStatus
    ): IImageLoader<ImageView> =
        GlideImageLoader(imageCache, networkStatus)
}