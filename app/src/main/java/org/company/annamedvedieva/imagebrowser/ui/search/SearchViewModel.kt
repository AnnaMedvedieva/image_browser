package org.company.annamedvedieva.imagebrowser.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.data.SearchResults
import org.company.annamedvedieva.imagebrowser.network.BrowserApi
import org.company.annamedvedieva.imagebrowser.network.BrowserApiStatus

const val COUNT_VALUE = 30
private const val TAG = "SearchViewModel"

class SearchViewModel : ViewModel() {

    private val _imageList = MutableLiveData<List<ImageItem>>()
    val imageList: LiveData<List<ImageItem>>
        get() = _imageList

    private val _status = MutableLiveData<BrowserApiStatus>()

    val status: LiveData<BrowserApiStatus>
        get() = _status

    private var resultsPage = 1

    fun clearResults() {
        _imageList.value = ArrayList()
        resultsPage = 1
    }

    fun loadSearchResults(query: String) {
        viewModelScope.launch {
            _status.value = BrowserApiStatus.LOADING
            try {
                val searchRes: SearchResults =
                    BrowserApi.retrofitService.getSearchResults(query, COUNT_VALUE, resultsPage)
                _imageList.value = searchRes.results
                _status.value = BrowserApiStatus.DONE
                resultsPage++
                Log.d(TAG, ": ${_status.value.toString()}")


            } catch (e: Exception) {
                _status.value = BrowserApiStatus.ERROR
                _imageList.value = ArrayList()
                Log.d(TAG, ": ${_status.value.toString()}")

            }
        }
    }
}