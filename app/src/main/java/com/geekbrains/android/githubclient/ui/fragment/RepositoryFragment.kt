package com.geekbrains.android.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.android.githubclient.R
import com.geekbrains.android.githubclient.mvp.presenter.RepositoryPresenter
import com.geekbrains.android.githubclient.mvp.view.RepositoryView
import com.geekbrains.android.githubclient.ui.ViewModels.RepositoryViewModel
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView {
    private lateinit var viewModel: RepositoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository, container, false)
    }

    companion object {
        fun newInstance() = RepositoryFragment()
    }

    private val presenter: RepositoryPresenter by moxyPresenter {
        RepositoryPresenter()
    }

    override fun setName(name: String?) {
        repository_name_text_view.text = String.format("Repository name: %s", name)
    }

    override fun isPrivate(isPrivate: Boolean?) {
        repository_visibility_text_view.text = String.format("Private: %s", isPrivate.toString())
    }

    override fun setForks(count: Int?) {
        repository_forks_text_view.text = String.format("Forks: %d", count)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProvider(activity!!).get(RepositoryViewModel::class.java)

        viewModel.getLiveData().observe(this, {
            presenter.showRepositoryInfo(it)
        })
    }
}