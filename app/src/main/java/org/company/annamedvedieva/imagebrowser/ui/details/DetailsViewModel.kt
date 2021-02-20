package org.company.annamedvedieva.imagebrowser.ui.details

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.data.ImageRepository
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(repository: ImageRepository) : ViewModel() {

    val imageRepository = repository

    lateinit var image: LiveData<ImageItem>

    private val _likeButton = MutableLiveData<Boolean>()
    val likeButton: LiveData<Boolean>
        get() = _likeButton


    private val _navigateBack = MutableLiveData<Boolean>()
    val navigateBack: LiveData<Boolean>
        get() = _navigateBack

    fun loadImage(imageId: String) {
        viewModelScope.launch {
            image = imageRepository.getImageById(imageId).asLiveData()
        }
    }

    fun setLikeButton(image: ImageItem) {
        _likeButton.value = image.isFavourite
    }

    fun onCloseButtonClick() {
        _navigateBack.value = true
    }

    fun navigationDone() {
        _navigateBack.value = false
    }

    fun onLikeButtonClick() {
        _likeButton.value = _likeButton.value != true
        if (_likeButton.value == true) {
            image.value!!.isFavourite = true
        } else image.value!!.isFavourite = false
    }

    fun onFragmentClosed() {
        viewModelScope.launch {
            if (image.value!!.isFavourite == true) {
                imageRepository.updateImage(image.value!!)
            } else imageRepository.deleteImage(image.value!!)
        }
    }

}

