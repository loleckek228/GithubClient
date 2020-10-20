package com.geekbrains.android.githubclient.mvp.model.dataSource

import com.geekbrains.android.githubclient.mvp.model.api.ApiService
import com.geekbrains.android.githubclient.mvp.model.entity.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser
import com.geekbrains.android.githubclient.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl(private val schedulersProvider: SchedulerProvider = SchedulerProvider()) :
    IDataSource {

    companion object {
        private const val BASE_URL_LOCATIONS = "https://api.github.com/"
    }

    override fun getUsers(): Single<List<GithubUser>> =
        getService().getUsers().subscribeOn(schedulersProvider.io())

    override fun getUserRepositories(url: String?): Single<List<GitHubUserRepository>> =
        getService().getUserRepository(url).subscribeOn(schedulersProvider.io())


    private fun getService(): ApiService =
        createRetrofit().create(ApiService::class.java)

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}