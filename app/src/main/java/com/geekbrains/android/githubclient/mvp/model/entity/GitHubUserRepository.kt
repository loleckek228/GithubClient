package com.geekbrains.android.githubclient.mvp.model.entity

import com.google.gson.annotations.Expose

data class GitHubUserRepository(
    @Expose val name: String?,
    @Expose val private: Boolean?,
    @Expose val forks: Int?
)