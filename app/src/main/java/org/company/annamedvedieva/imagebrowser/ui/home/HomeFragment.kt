package org.company.annamedvedieva.imagebrowser.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.company.annamedvedieva.imagebrowser.databinding.FragmentHomeBinding
import org.company.annamedvedieva.imagebrowser.ui.ImageGridAdapter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater)

        binding.viewmodel = homeViewModel

        binding.imageRecyclerView.adapter = ImageGridAdapter(ImageGridAdapter.OnClickListener {
            homeViewModel.navigateToImageDetails(it)
        })

        homeViewModel.selectedPicture.observe(viewLifecycleOwner, {
            if (it != null) {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionNavigationHomeToDetailsFragment(it.id))
                homeViewModel.doneNavigating()
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}