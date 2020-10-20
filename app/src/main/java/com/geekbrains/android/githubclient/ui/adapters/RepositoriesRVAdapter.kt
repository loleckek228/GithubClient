package com.geekbrains.android.githubclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.android.githubclient.R
import com.geekbrains.android.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.geekbrains.android.githubclient.mvp.view.itemsView.RepositoryItemView
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoriesRVAdapter(
    val presenter: IRepositoryListPresenter
) : RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(
                holder
            )
        }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        RepositoryItemView {
        override var pos = -1

        override fun setRepository(repository: String?) {
            with(itemView) {
                repository_text_view.text = repository
            }
        }
    }
}