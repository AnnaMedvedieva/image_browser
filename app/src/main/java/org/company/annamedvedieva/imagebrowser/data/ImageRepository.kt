package org.company.annamedvedieva.imagebrowser.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepository @Inject constructor(private val imageDao: ImageDao) {

    suspend fun insertImage(image: ImageItem) {
        imageDao.insertImage(image)
    }

    val allFavourites: Flow<List<ImageItem>> = imageDao.loadFavourites()

    suspend fun deleteImage(image: ImageItem) {
        imageDao.deletePhoto(image)
    }


}