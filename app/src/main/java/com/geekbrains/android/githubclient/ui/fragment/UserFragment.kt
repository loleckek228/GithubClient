package com.geekbrains.android.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.android.githubclient.GithubApp
import com.geekbrains.android.githubclient.R
import com.geekbrains.android.githubclient.mvp.presenter.UserPresenter
import com.geekbrains.android.githubclient.mvp.view.UserView
import com.geekbrains.android.githubclient.ui.BackButtonListener
import com.geekbrains.android.githubclient.ui.UserViewModel
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private lateinit var viewModel: UserViewModel

    companion object {
        fun newInstance() = UserFragment()
    }

    val presenter: UserPresenter by moxyPresenter { UserPresenter(GithubApp.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return View.inflate(context, R.layout.fragment_user, null)
    }

    override fun showUserInfo(info: String) {
        user_login_textView.text = info
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(activity!!).get(UserViewModel::class.java)

        viewModel.getUserLoginLiveData().observe(this, {
            presenter.showUserInfo(it)
        })
    }
}