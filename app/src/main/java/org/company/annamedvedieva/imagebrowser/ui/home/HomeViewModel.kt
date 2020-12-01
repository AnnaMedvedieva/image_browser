package org.company.annamedvedieva.imagebrowser.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.network.BrowserApi
import org.company.annamedvedieva.imagebrowser.network.BrowserApiStatus

private const val TAG = "HomeViewModel"
const val DEFAULT_COUNT_VALUE = 30

class HomeViewModel : ViewModel() {


    private val _imageList = MutableLiveData<List<ImageItem>>()
    val imageList: LiveData<List<ImageItem>>
        get() = _imageList

    private val _status = MutableLiveData<BrowserApiStatus>()

    val status: LiveData<BrowserApiStatus>
        get() = _status

    init {
        loadRandomImagesList()
        Log.d(org.company.annamedvedieva.imagebrowser.ui.home.TAG, _status.value.toString())
    }

    private fun loadRandomImagesList() {
        viewModelScope.launch {
            _status.value = BrowserApiStatus.LOADING
            try {
                _imageList.value = BrowserApi.retrofitService.getRandomPictures(DEFAULT_COUNT_VALUE)
                _status.value = BrowserApiStatus.DONE
                Log.d(org.company.annamedvedieva.imagebrowser.ui.home.TAG, _status.value.toString())

            } catch (e: Exception) {
                _status.value = BrowserApiStatus.ERROR
                _imageList.value = ArrayList()
                Log.d(org.company.annamedvedieva.imagebrowser.ui.home.TAG, _status.value.toString())

            }
        }


    }

//     private fun loadRandomImagesList(){
//         BrowserApi.retrofitService.getRandomPictures().enqueue(
//             object : Callback<String> {
//                 override fun onFailure(call: Call<String>, t: Throwable) {
//                     _response.value = "Failure: " + t.message
//                 }
//
//                 override fun onResponse(
//                     call: Call<String>,
//                     response: Response<String>
//                 ) {
//                     _response.value = response.body()
//                     Log.d(TAG, "onResponse: ${_response.value.toString()}")
//                 }
//             })
//     }
}