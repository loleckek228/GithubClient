package com.geekbrains.android.githubclient.mvp.model.cache

import android.graphics.Bitmap
import com.geekbrains.android.githubclient.GithubApp
import com.geekbrains.android.githubclient.mvp.model.cache.icache.IRoomGithubImageCache
import com.geekbrains.android.githubclient.mvp.model.dataSource.local.Database
import com.geekbrains.android.githubclient.mvp.model.entity.local.RoomGithubImage
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class RoomGithubImageCache(private val database: Database) : IRoomGithubImageCache {
    private val IMAGE_FOLDER_NAME = "image"

    override fun getFile(url: String?): Maybe<File?>? {
        return Maybe.fromCallable {
            val cachedImage: RoomGithubImage = database.imageDao().findByUrl(url!!)

            cachedImage?.let {
                File(cachedImage.path)
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun saveImage(url: String?, bitmap: Bitmap?): Maybe<File?>? {
        val fileFormat = if (url!!.contains(".jpg")) ".jpg" else ".png"
        val imageFile = File(getImageDir(), SHA1(url) + fileFormat)
        val fos: FileOutputStream

        try {
            fos = FileOutputStream(imageFile)
            bitmap!!.compress(
                if (fileFormat == "jpg") Bitmap.CompressFormat.JPEG else Bitmap.CompressFormat.PNG,
                100,
                fos
            )
            fos.close()
        } catch (e: Exception) {
            Timber.d("Failed to save image")
            return null
        }

        return Maybe.create<File> {
            val image = RoomGithubImage(url, imageFile.toString())
            database.imageDao().insert(image)
            it.onSuccess(imageFile)
        }.subscribeOn(Schedulers.io()).cast(File::class.java)
    }

    override fun getImageDir(): File? {
        return File(
            GithubApp.instance.getExternalFilesDir(null).toString() + "/" + IMAGE_FOLDER_NAME
        )
    }

    override fun SHA1(s: String?): String? {
        var m: MessageDigest? = null

        try {
            m = MessageDigest.getInstance("SHA1")
        } catch (e: NoSuchAlgorithmException) {
            Timber.e(e)
        }

        m!!.update(s!!.toByteArray(), 0, s.length)
        return BigInteger(1, m.digest()).toString(16)
    }

    override fun deleteFileOrDirRecursive(fileOrDirectory: File?) {
        if (fileOrDirectory!!.isDirectory) {
            fileOrDirectory.listFiles().map {
                deleteFileOrDirRecursive(it)
            }
        }

        fileOrDirectory.delete()
    }

    override fun getFileOrDirSize(f: File?): Long {
        var size: Long = 0

        if (f!!.isDirectory) {
            f.listFiles().map {
                size += getFileOrDirSize(it)
            }
        } else {
            size = f.length()
        }

        return size
    }
}