package org.company.annamedvedieva.imagebrowser.ui.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iammert.library.ui.multisearchviewlib.MultiSearchView
import dagger.hilt.android.AndroidEntryPoint

import org.company.annamedvedieva.imagebrowser.databinding.FragmentSearchBinding
import org.company.annamedvedieva.imagebrowser.ui.ImageGridAdapter

private const val TAG = "SearchFragment"

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private var searchQueries: ArrayList<CharSequence> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSearchBinding.inflate(inflater)

        binding.searchViewModel = searchViewModel

        binding.searchRecyclerView.adapter = ImageGridAdapter(ImageGridAdapter.OnClickListener {
            searchViewModel.navigateToImageDetails(it)
        })

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
                view?.let { activity?.hideKeyboard(it) }
            }

            override fun onSearchItemRemoved(index: Int) {
                Log.d(TAG, "onSearchItemRemoved:$index ")
                searchViewModel.clearResults()

                if (searchQueries.isNotEmpty()) {
                    searchQueries.removeAt(index)
                }

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

        searchViewModel.selectedPicture.observe(viewLifecycleOwner, {
            if (it != null) {
                this.findNavController()
                    .navigate(SearchFragmentDirections.actionNavigationSearchToDetailsFragment(it.id))
                searchViewModel.doneNavigating()
            }
        })

        return binding.root
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}