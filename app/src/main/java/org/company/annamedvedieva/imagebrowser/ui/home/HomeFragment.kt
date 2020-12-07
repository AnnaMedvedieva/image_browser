package org.company.annamedvedieva.imagebrowser.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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

        binding.imageRecyclerView.adapter = ImageGridAdapter()

        binding.lifecycleOwner = this

        return binding.root
    }
}