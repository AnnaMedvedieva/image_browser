package org.company.annamedvedieva.imagebrowser.ui.favourites


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.data.ImageRepository
import org.company.annamedvedieva.imagebrowser.network.BrowserApiStatus

class FavouritesViewModel @ViewModelInject constructor(repository: ImageRepository) : ViewModel() {

    val imageRepository = repository

    lateinit var imageList: LiveData<List<ImageItem>>

    private val _selectedPicture = MutableLiveData<ImageItem>()
    val selectedPicture: LiveData<ImageItem>
        get() = _selectedPicture

    init {
        loadFavourites()
    }

    private fun loadFavourites() {
        viewModelScope.launch {
            imageList = imageRepository.allFavourites.asLiveData()
        }
    }

    fun navigateToImageDetails(image: ImageItem) {
        _selectedPicture.value = image
        insertImage(image)
    }

    fun doneNavigating() {
        _selectedPicture.value = null
    }

    fun insertImage(image: ImageItem) {
        viewModelScope.launch {
            imageRepository.insertImage(image)
        }
    }

}
