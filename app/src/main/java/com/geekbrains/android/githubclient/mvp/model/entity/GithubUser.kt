package com.geekbrains.android.githubclient.mvp.model.entity

import com.google.gson.annotations.Expose

data class GithubUser(
    @Expose val id: String?,
    @Expose val login: String?,
    @Expose val avatar_url: String?,
    @Expose val repos_url: String?
)