package org.company.annamedvedieva.imagebrowser.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.company.annamedvedieva.imagebrowser.databinding.FragmentSearchBinding
import org.company.annamedvedieva.imagebrowser.ui.ImageGridAdapter

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSearchBinding.inflate(inflater)

        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        binding.searchViewModel = searchViewModel

        binding.searchRecyclerView.adapter = ImageGridAdapter()

        val layoitm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.searchRecyclerView.layoutManager = layoitm

        layoitm.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        binding.lifecycleOwner = this

        return binding.root
    }
}