package org.company.annamedvedieva.imagebrowser.data

import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun insertImage(image: ImageItem)

    fun getAllFavourites(): Flow<List<ImageItem>>

    suspend fun deleteImage(image: ImageItem)

    fun getImageById(imageId: String): Flow<ImageItem>

    suspend fun updateImage(image: ImageItem)
}