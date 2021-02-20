package org.company.annamedvedieva.imagebrowser.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultImageRepository @Inject constructor(private val imageDao: ImageDao) : ImageRepository {

    override suspend fun insertImage(image: ImageItem) = withContext(Dispatchers.IO) {
        imageDao.insertImage(image)
    }

    override fun getAllFavourites(): Flow<List<ImageItem>> {
        return imageDao.loadFavourites()
    }

    override suspend fun deleteImage(image: ImageItem) = withContext(Dispatchers.IO) {
        imageDao.deletePhoto(image)
    }

    override fun getImageById(imageId: String): Flow<ImageItem> {
        return imageDao.getImageById(imageId)
    }

    override suspend fun updateImage(image: ImageItem) = withContext(Dispatchers.IO) {
        imageDao.updateImage(image)
    }

}