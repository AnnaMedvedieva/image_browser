package org.company.annamedvedieva.imagebrowser.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.data.ImageRepository

class DetailsViewModel @ViewModelInject constructor(repository: ImageRepository) : ViewModel() {

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

    fun onCloseButtonClick() {
        _navigateBack.value = true
    }

    fun navigationDone() {
        _navigateBack.value = false
    }

    fun onLikeButtonClick() {
        _likeButton.value = _likeButton.value != true
    }

}