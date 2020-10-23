package com.geekbrains.android.githubclient.mvp.model.cache.icache

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Maybe
import java.io.File

interface IRoomGithubImageCache {
    fun getFile(url: String?): Maybe<File?>?
    fun saveImage(url: String?, bitmap: Bitmap?): Maybe<File?>?
    fun getImageDir(): File?
    fun SHA1(s: String?): String?
    fun deleteFileOrDirRecursive(fileOrDirectory: File?)
    fun getFileOrDirSize(f: File?): Long
}