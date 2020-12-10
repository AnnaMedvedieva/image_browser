package org.company.annamedvedieva.imagebrowser.ui.favourites


import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.data.ImageRepository
import org.company.annamedvedieva.imagebrowser.network.BrowserApiStatus

private const val TAG = "FavouritesViewModel"

class FavouritesViewModel @ViewModelInject constructor(repository: ImageRepository) : ViewModel() {

    lateinit var imageList: LiveData<List<ImageItem>>

    private val _status = MutableLiveData<BrowserApiStatus>()

    val status: LiveData<BrowserApiStatus>
        get() = _status


    val imageRepository = repository

    init {
        loadFavourites()
    }

    private fun loadFavourites() {
        _status.value = BrowserApiStatus.LOADING
        viewModelScope.launch {
            try {
                imageList = imageRepository.allFavourites.asLiveData()
                _status.value = BrowserApiStatus.DONE
                Log.d(TAG, "loadFavourites: ${_status.value.toString()}")
            } catch (e: Exception) {
                _status.value = BrowserApiStatus.ERROR
                //_imageList.value = ArrayList()
                Log.d(TAG, "loadRandomImagesList: ${_status.value.toString()}")

            }
        }
    }
}
