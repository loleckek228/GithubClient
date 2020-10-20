package com.geekbrains.android.githubclient.mvp.model.dataSource.remote

import com.geekbrains.android.githubclient.mvp.model.api.ApiService
import com.geekbrains.android.githubclient.mvp.model.dataSource.IDataSource
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl :
    IDataSource {

    companion object {
        private const val BASE_URL_LOCATIONS = "https://api.github.com/"
    }

    private fun getService(): ApiService =
        createRetrofit().create(ApiService::class.java)

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    override fun getUsers(): Single<List<GithubUser>> {
        return getService().getUsers()
    }

    override fun getUserRepositories(url: String?): Single<List<GitHubUserRepository>> {
        return getService().getUserRepository(url)
    }
}