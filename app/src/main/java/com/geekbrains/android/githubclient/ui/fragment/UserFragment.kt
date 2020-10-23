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
import com.geekbrains.android.githubclient.mvp.model.entity.remote.GitHubUserRepository
import com.geekbrains.android.githubclient.mvp.model.repository.UserRepo
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUsersRepo
import com.geekbrains.android.githubclient.mvp.model.repository.UsersRepo
import com.geekbrains.android.githubclient.mvp.model.repository.irepo.IUserRepo
import com.geekbrains.android.githubclient.mvp.presenter.UserPresenter
import com.geekbrains.android.githubclient.mvp.view.UserView
import com.geekbrains.android.githubclient.mvp.view.image.GlideImageLoader
import com.geekbrains.android.githubclient.mvp.view.image.IImageLoader
import com.geekbrains.android.githubclient.ui.BackButtonListener
import com.geekbrains.android.githubclient.ui.ViewModels.RepositoryViewModel
import com.geekbrains.android.githubclient.ui.ViewModels.UserViewModel
import com.geekbrains.android.githubclient.ui.adapters.RepositoriesRVAdapter
import com.geekbrains.android.githubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    private val imageLoader: IImageLoader<ImageView> = GlideImageLoader(
        RoomGithubImageCache(Database.getInstance()!!),
        AndroidNetworkStatus()
    )

    private lateinit var userViewModel: UserViewModel
    private lateinit var repositoryViewModel: RepositoryViewModel
    private var mAdapter: RepositoriesRVAdapter? = null

    companion object {
        fun newInstance() = UserFragment()
    }

    val presenter: UserPresenter by moxyPresenter {
        val api: IDataSource = RetrofitImpl()
        val usersRepo: IUserRepo =
            UserRepo(
                api,
                AndroidNetworkStatus(),
                RoomGithubRepositoriesCache(Database.getInstance()!!)
            )

        UserPresenter(
            GithubApp.instance.router,
            AndroidSchedulers.mainThread(),
            usersRepo
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return View.inflate(context, R.layout.fragment_user, null)
    }

    override fun init() {
        mAdapter = RepositoriesRVAdapter(presenter.repositoriesListPresenter)

        with(repositories_recycler_view) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun setLogin(login: String?) {
        user_login_text_view.text = login
    }

    override fun loadImage(url: String?) {
        imageLoader.loadImage(url, user_avatar_image_view)
    }

    override fun updateList() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun sendRepository(repository: GitHubUserRepository?) {
        repositoryViewModel.getLiveData().postValue(repository)
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userViewModel =
            ViewModelProvider(activity!!).get(UserViewModel::class.java)

        repositoryViewModel =
            ViewModelProvider(activity!!).get(RepositoryViewModel::class.java)

        userViewModel.getLiveData().observe(this, {
            presenter.showUserInfo(it)
        })
    }
}