package com.geekbrains.android.githubclient.mvp.view.image

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadImage(url: String?, container: ImageView) {
        Glide.with(container.context).load(url).into(container)
    }
}