package org.company.annamedvedieva.imagebrowser.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.company.annamedvedieva.imagebrowser.R

class FavouritesFragment : Fragment() {

    private lateinit var favouritesViewModel: FavouritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel =
            ViewModelProvider(this).get(FavouritesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favourites, container, false)

        return root
    }
}