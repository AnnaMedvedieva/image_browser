package org.company.annamedvedieva.imagebrowser.data

import android.media.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepository @Inject constructor(private val imageDao: ImageDao) {

    suspend fun insertImage(image: ImageItem) = withContext(Dispatchers.IO) {
        imageDao.insertImage(image)
    }

    val allFavourites: Flow<List<ImageItem>> = imageDao.loadFavourites()

    suspend fun deleteImage(image: ImageItem) = withContext(Dispatchers.IO) {
        imageDao.deletePhoto(image)
    }

    fun getImageById(imageId: String): Flow<ImageItem> {
        return imageDao.getImageById(imageId)
    }

}