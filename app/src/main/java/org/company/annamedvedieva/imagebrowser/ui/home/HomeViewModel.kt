package org.company.annamedvedieva.imagebrowser.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.data.ImageRepository
import org.company.annamedvedieva.imagebrowser.network.BrowserApi
import org.company.annamedvedieva.imagebrowser.network.BrowserApiStatus

const val DEFAULT_COUNT_VALUE = 30
private const val TAG = "HomeViewModel"

class HomeViewModel @ViewModelInject constructor(repository: ImageRepository) : ViewModel() {

    private val _imageList = MutableLiveData<List<ImageItem>>()
    val imageList: LiveData<List<ImageItem>>
        get() = _imageList

    val imageRepository = repository

    private val _status = MutableLiveData<BrowserApiStatus>()
    val status: LiveData<BrowserApiStatus>
        get() = _status

    private val _selectedPicture = MutableLiveData<ImageItem>()
    val selectedPicture: LiveData<ImageItem>
        get() = _selectedPicture

    init {
        loadRandomImagesList()
    }

    private fun loadRandomImagesList() {
        viewModelScope.launch {
            _status.value = BrowserApiStatus.LOADING
            try {
                _imageList.value = BrowserApi.retrofitService.getRandomPictures(DEFAULT_COUNT_VALUE)
                _status.value = BrowserApiStatus.DONE
                Log.d(TAG, "loadRandomImagesList: ${_status.value.toString()}")


            } catch (e: Exception) {
                _status.value = BrowserApiStatus.ERROR
                _imageList.value = ArrayList()
                Log.d(TAG, "loadRandomImagesList: ${_status.value.toString()}")

            }
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