package org.company.annamedvedieva.imagebrowser.ui.favourites


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.data.ImageRepository


class FavouritesViewModel @ViewModelInject constructor(repository: ImageRepository) : ViewModel() {

    private val _imageList = MutableLiveData<List<ImageItem>>()
    val imageList: LiveData<List<ImageItem>>
        get() = _imageList


    val imageRepository = repository

    init {
        loadRandomImagesList()
    }

    private fun loadRandomImagesList() {
        viewModelScope.launch {
            try {
                _imageList.value = imageRepository.allFavourites.asLiveData().value

            } catch (e: Exception) {
                _imageList.value = ArrayList()
            }
        }

    }
}
