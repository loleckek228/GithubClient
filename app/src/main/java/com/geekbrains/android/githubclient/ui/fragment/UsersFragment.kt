package com.geekbrains.android.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.android.githubclient.GithubApp
import com.geekbrains.android.githubclient.R
import com.geekbrains.android.githubclient.mvp.model.Repository.GithubUsersRepo
import com.geekbrains.android.githubclient.mvp.model.entity.GithubUser
import com.geekbrains.android.githubclient.mvp.presenter.UsersPresenter
import com.geekbrains.android.githubclient.mvp.view.UsersView
import com.geekbrains.android.githubclient.ui.BackButtonListener
import com.geekbrains.android.githubclient.ui.UserViewModel
import com.geekbrains.android.githubclient.ui.adapter.UsersRVAdapter
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private lateinit var viewModel: UserViewModel

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepo(),
            GithubApp.instance.router
        )
    }

    var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun sendUser(user: GithubUser) {
        viewModel.getUserLoginLiveData().postValue(user)
    }

    override fun backPressed() = presenter.backPressed()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(UserViewModel::class.java)
    }
}