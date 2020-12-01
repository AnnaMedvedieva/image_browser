package org.company.annamedvedieva.imagebrowser.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.company.annamedvedieva.imagebrowser.databinding.FragmentHomeBinding
import org.company.annamedvedieva.imagebrowser.ui.ImageGridAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.viewmodel = homeViewModel

        binding.imageRecyclerView.adapter = ImageGridAdapter()

        binding.lifecycleOwner = this

        return binding.root
    }
}