package org.company.annamedvedieva.imagebrowser.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.company.annamedvedieva.imagebrowser.data.ImageItem

class SearchViewModel : ViewModel() {

    private val _imageList = MutableLiveData<List<ImageItem>>()
    val imageList: LiveData<List<ImageItem>>
        get() = _imageList

    //Temporary fake list implementation
    val listTemp = listOf(
        ImageItem(
            0,
            "https://images.unsplash.com/photo-1566853024045-ccf08017f7cc?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDF8dG93SlpGc2twR2d8fGVufDB8fHw%3D&auto=format&fit=crop&w=500&q=60",
            isFavourite = true
        ),
        ImageItem(
            1,
            "https://images.unsplash.com/photo-1597515449381-b57ede1fa281?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDJ8dG93SlpGc2twR2d8fGVufDB8fHw%3D&auto=format&fit=crop&w=500&q=60",
            isFavourite = false
        ),
        ImageItem(
            2,
            "https://images.unsplash.com/photo-1560873881-f281b8f8609e?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDR8dG93SlpGc2twR2d8fGVufDB8fHw%3D&auto=format&fit=crop&w=500&q=60",
            isFavourite = true
        ),
        ImageItem(
            3,
            "https://images.unsplash.com/photo-1531409193861-86924ea076f3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80",
            isFavourite = true
        ),
        ImageItem(
            4,
            "https://images.unsplash.com/photo-1521397896463-a8457690ee70?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80",
            isFavourite = false
        ),
        ImageItem(
            5,
            "https://images.unsplash.com/photo-1464746133101-a2c3f88e0dd9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1027&q=80",
            isFavourite = true
        ),
        ImageItem(
            6,
            "https://images.unsplash.com/photo-1520466809213-7b9a56adcd45?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80",
            isFavourite = true
        ),
        ImageItem(
            7,
            "https://images.unsplash.com/photo-1597564656269-6ddb83672aa6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80",
            isFavourite = false
        ),
        ImageItem(
            8,
            "https://images.unsplash.com/photo-1605702098418-c2fc325dead0?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDIwfFM0TUtMQXNCQjc0fHxlbnwwfHx8&auto=format&fit=crop&w=500&q=60",
            isFavourite = true
        ),
        ImageItem(
            9,
            "https://images.unsplash.com/photo-1550281378-521929a11c42?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDI5fFM0TUtMQXNCQjc0fHxlbnwwfHx8&auto=format&fit=crop&w=500&q=60",
            isFavourite = true
        )
    )

    init {
        _imageList.value = listTemp
    }

}