package org.company.annamedvedieva.imagebrowser.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.iammert.library.ui.multisearchviewlib.MultiSearchView
import org.company.annamedvedieva.imagebrowser.databinding.FragmentSearchBinding
import org.company.annamedvedieva.imagebrowser.ui.ImageGridAdapter
import kotlin.math.log

private const val TAG = "SearchFragment"

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var searchQueries: ArrayList<CharSequence> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSearchBinding.inflate(inflater)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        binding.searchViewModel = searchViewModel

        binding.searchRecyclerView.adapter = ImageGridAdapter()

        binding.lifecycleOwner = this

        binding.multiSearchView.setSearchViewListener(object :
            MultiSearchView.MultiSearchViewListener {
            override fun onItemSelected(index: Int, s: CharSequence) {
                Log.d(TAG, "onItemSelected: $index")
                searchViewModel.loadSearchResults(s.toString())
            }

            override fun onTextChanged(index: Int, s: CharSequence) {
                Log.d(TAG, "onTextChanged: ")
            }

            override fun onSearchComplete(index: Int, s: CharSequence) {
                Log.d(TAG, "onSearchComplete: $s")

                searchQueries.add(s)

                searchViewModel.loadSearchResults(s.toString())

            }

            override fun onSearchItemRemoved(index: Int) {
                Log.d(TAG, "onSearchItemRemoved:$index ")
                searchViewModel.clearResults()
                searchQueries.removeAt(index)

                if (searchQueries.isNotEmpty()) {
                    when (index) {
                        0 -> searchViewModel.loadSearchResults(searchQueries[0].toString())
                        searchQueries.lastIndex + 1 -> searchViewModel.loadSearchResults(
                            searchQueries[index - 1].toString()
                        )
                        else -> searchViewModel.loadSearchResults(searchQueries[index].toString())
                    }
                }
            }
        })

        return binding.root
    }
}