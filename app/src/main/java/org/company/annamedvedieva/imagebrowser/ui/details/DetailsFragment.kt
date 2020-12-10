package org.company.annamedvedieva.imagebrowser.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.company.annamedvedieva.imagebrowser.R
import org.company.annamedvedieva.imagebrowser.databinding.FragmentDetailsBinding

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDetailsBinding.inflate(inflater)

        binding.viewmodel = detailsViewModel

        val imageId = DetailsFragmentArgs.fromBundle(requireArguments()).imageId

        detailsViewModel.navigateBack.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController().popBackStack()
                detailsViewModel.navigationDone()
            }
        })

        detailsViewModel.likeButton.observe(viewLifecycleOwner, {
            if (it == true) {
                binding.likeButton.setImageResource(R.drawable.ic_baseline_favorite_white_24)
            } else binding.likeButton.setImageResource(R.drawable.ic_baseline_favorite_24)

        })

        detailsViewModel.loadImage(imageId)

        binding.lifecycleOwner = this

        return binding.root
    }


}