package com.geekbrains.android.githubclient.mvp.view.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubImageCache
import com.geekbrains.android.githubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

class GlideImageLoader(
    private val imageCache: IRoomGithubImageCache,
    private val networkStatus: AndroidNetworkStatus
) : IImageLoader<ImageView> {

    override fun loadImage(url: String?, container: ImageView) {
        networkStatus.isOnline().subscribe {
            if (it) {
                Glide.with(container.context).asBitmap()
                    .load(url)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Timber.e(e, "Image load failed")

                            return false
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            imageCache.saveImage(url, resource)

                            return false
                        }
                    }).into(container)
            } else {
                imageCache.getFile(url)?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { file ->
                        Glide.with(container.context)
                            .load(file)
                            .into(container)
                    }
            }
        }

    }
}