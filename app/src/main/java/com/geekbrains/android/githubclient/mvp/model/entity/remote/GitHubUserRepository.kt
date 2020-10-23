package com.geekbrains.android.githubclient.mvp.model.entity.remote

import com.google.gson.annotations.Expose

data class GitHubUserRepository(
    @Expose val name: String?,
    @Expose val forks: Int?,
    @Expose val private: Boolean?,
)