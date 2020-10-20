package com.geekbrains.android.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.android.githubclient.GithubApp
import com.geekbrains.android.githubclient.R
import com.geekbrains.android.githubclient.mvp.model.cache.RoomGithubImageCache
import com.geekbrains.android.githubclient.mvp.model.cache.RoomGithubRepositoriesCache
import com.geekbrains.android.githubclient.mvp.model.cache.RoomGithubUsersCache
import com.geekbrains.android.githubclient.mvp.model.dataSource.IDataSource
import com.geekbrains.android.githubclient.mvp.model.dataSource.local.Database
import com.geekbrains.android.githubclient.mvp.model.dataSource.remote.RetrofitImpl
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GithubUser
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUsersRepo
import com.geekbrains.android.githubclient.mvp.model.repository.UsersRepo
import com.geekbrains.android.githubclient.mvp.presenter.UsersPresenter
import com.geekbrains.android.githubclient.mvp.view.UsersView
import com.geekbrains.android.githubclient.mvp.view.image.GlideImageLoader
import com.geekbrains.android.githubclient.mvp.view.image.IImageLoader
import com.geekbrains.android.githubclient.ui.BackButtonListener
import com.geekbrains.android.githubclient.ui.ViewModels.UserViewModel
import com.geekbrains.android.githubclient.ui.adapters.UsersRVAdapter
import com.geekbrains.android.githubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private lateinit var viewModel: UserViewModel

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        val api: IDataSource = RetrofitImpl()
        val usersRepo: IUsersRepo =
            UsersRepo(
                api,
                AndroidNetworkStatus(),
                RoomGithubUsersCache(Database.getInstance()!!)
            )

        UsersPresenter(
            GithubApp.instance.router,
            AndroidSchedulers.mainThread(),
            usersRepo
        )
    }

    private var mAdapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        val imageLoader: IImageLoader<ImageView> = GlideImageLoader(
            RoomGithubImageCache(Database.getInstance()!!),
            AndroidNetworkStatus()
        )

        mAdapter = UsersRVAdapter(presenter.usersListPresenter, imageLoader)

        with(users_recycler_view) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun updateList() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun sendUser(user: GithubUser) {
        viewModel.getLiveData().postValue(user)
    }

    override fun backPressed() = presenter.backPressed()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(activity!!).get(UserViewModel::class.java)

    }
}