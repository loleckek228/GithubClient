package com.geekbrains.android.githubclient.di.module

import com.geekbrains.android.githubclient.mvp.model.api.IDataSource
import com.geekbrains.android.githubclient.mvp.model.network.INetworkStatus
import com.geekbrains.android.githubclient.ui.network.AndroidNetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Named("base_url")
    @Provides
    fun baseUrl() = "https://api.github.com/"

    @Provides
    fun api(@Named("base_url") baseUrl: String, gson: Gson): IDataSource =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)

    @Singleton
    @Provides
    fun gson() =
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

    @Singleton
    @Provides
    fun networkStatus(): INetworkStatus = AndroidNetworkStatus()
}