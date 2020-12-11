package org.company.annamedvedieva.imagebrowser.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.company.annamedvedieva.imagebrowser.R
import org.company.annamedvedieva.imagebrowser.databinding.FragmentFavouritesBinding
import org.company.annamedvedieva.imagebrowser.databinding.FragmentHomeBinding
import org.company.annamedvedieva.imagebrowser.ui.ImageGridAdapter
import org.company.annamedvedieva.imagebrowser.ui.home.HomeFragmentDirections

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private val favouritesViewModel: FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFavouritesBinding.inflate(inflater)

        binding.viewmodel = favouritesViewModel

        binding.imageRecyclerView.adapter = ImageGridAdapter(ImageGridAdapter.OnClickListener {
            favouritesViewModel.navigateToImageDetails(it)
        })

        favouritesViewModel.selectedPicture.observe(viewLifecycleOwner, {
            if (it != null) {
                this.findNavController()
                    .navigate(
                        FavouritesFragmentDirections.actionNavigationFavouritesToDetailsFragment(
                            it.id
                        )
                    )
                favouritesViewModel.doneNavigating()
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}