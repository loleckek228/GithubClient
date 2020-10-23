package com.geekbrains.android.githubclient.ui.activity

import android.os.Bundle
import com.geekbrains.android.githubclient.GithubApp
import com.geekbrains.android.githubclient.R
import com.geekbrains.android.githubclient.mvp.presenter.MainPresenter
import com.geekbrains.android.githubclient.mvp.view.MainView
import com.geekbrains.android.githubclient.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private val presenter: MainPresenter
            by moxyPresenter {
                MainPresenter().apply {
                    GithubApp.instance.appComponent.inject(this)
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        GithubApp.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }

        presenter.backClicked()
    }
}