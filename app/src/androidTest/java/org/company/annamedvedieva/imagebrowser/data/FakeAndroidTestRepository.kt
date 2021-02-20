package org.company.annamedvedieva.imagebrowser.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.data.ImageRepository

class FakeAndroidTestRepository : ImageRepository {

    private val imageData = LinkedHashMap<String, ImageItem>()


    private val images = mutableListOf<ImageItem>()
    private val observableImages = MutableLiveData<List<ImageItem>>(images)

    private fun refreshLivedata() {
        observableImages.postValue(images)
    }

    override suspend fun insertImage(image: ImageItem) {
        images.add(image)
        refreshLivedata()
    }

    override fun getAllFavourites(): Flow<List<ImageItem>> {
        return observableImages.asFlow()
    }

    override suspend fun deleteImage(image: ImageItem) {
        images.remove(image)
        refreshLivedata()
    }

    override fun getImageById(imageId: String): Flow<ImageItem> {

        return flow {
            emit(imageData[imageId]!!)
        }
    }

    override suspend fun updateImage(image: ImageItem) {
        imageData[image.id] = image
    }
}