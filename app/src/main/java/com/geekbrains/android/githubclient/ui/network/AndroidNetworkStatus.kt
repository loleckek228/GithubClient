package com.geekbrains.android.githubclient.ui.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.geekbrains.android.githubclient.GithubApp
import com.geekbrains.android.githubclient.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber

class AndroidNetworkStatus : INetworkStatus {
    companion object {
        private const val VERBOSE = true
    }

    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        statusSubject.onNext(false)

        val connectivityManager =
            GithubApp.instance.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val request = NetworkRequest.Builder().build()

        connectivityManager.registerNetworkCallback(request, object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                if (VERBOSE) {
                    Timber.d("onAvailable")
                }

                statusSubject.onNext(true)
            }

            override fun onUnavailable() {
                if (VERBOSE) {
                    Timber.d("onUnavailable")
                }

                statusSubject.onNext(false)
            }

            override fun onLost(network: Network) {
                if (VERBOSE) {
                    Timber.d("onLost")
                }

                statusSubject.onNext(false)
            }
        })
    }

    override fun isOnline(): Observable<Boolean> = statusSubject

    override fun isOnlineSingle(): Single<Boolean> = statusSubject.first(false)
}