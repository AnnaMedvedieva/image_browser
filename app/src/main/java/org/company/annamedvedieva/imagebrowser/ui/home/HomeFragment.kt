package org.company.annamedvedieva.imagebrowser.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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

        // binding.imageRecyclerView

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            //binding.textHome.text = it
//        })
        binding.viewmodel = homeViewModel

        binding.imageRecyclerView.adapter = ImageGridAdapter()

        val layoitm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.imageRecyclerView.layoutManager = layoitm

        layoitm.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        binding.lifecycleOwner = this

        return binding.root
    }
}