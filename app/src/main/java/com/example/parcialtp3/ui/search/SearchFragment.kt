package com.example.parcialtp3.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.R
import com.example.parcialtp3.data.dao.DogDao
import com.example.parcialtp3.databinding.FragmentSearchBinding
import com.example.parcialtp3.ui.adapter.adapter.DogAdapter
import com.example.parcialtp3.ui.adapter.adapter.DogListener
import com.example.parcialtp3.ui.adapter.adapter.SaveIconListener
import com.example.parcialtp3.ui.home.CheckboxListAdapter
import com.example.parcialtp3.ui.home.FilterDialogViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "SearchFragment"
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val breedList = mutableListOf<String>()

    private val dogAdapter = DogAdapter(DogListener { dog, id ->

    }, SaveIconListener { id ->
        // Handle save icon click
    })

    private lateinit var dogRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        //binding.lifecycleOwner = viewLifecycleOwner not working
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        // Find the RecyclerView by ID
        dogRecyclerView = view.findViewById(R.id.recyclerView)

        // Set up the DogListAdapter
        dogRecyclerView.adapter = dogAdapter

        // Specify the layout manager (e.g., LinearLayoutManager)
        dogRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
            }
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)

                val menuItem = menu.findItem(R.id.search)
                val searchView = menuItem.actionView as SearchView

                searchView.queryHint = getString(R.string.search_hint)

                menuItem.expandActionView()
                searchView.doOnLayout {
                    //searchView.clearFocus()
                }
                menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                    override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                        return true
                    }
                    override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                        findNavController().navigateUp()
                        return true
                    }
                })
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText != null && newText.length > 3) {
                            fetchBreeds(newText)
                            // Actualiza tu UI con las razas y subrazas subrazas encontradas
                        }
                        return true
                    }
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED).also {
        }
    }
    private fun fetchBreeds(query: String) {
        viewModel.getAvailableDogsByBreed(query).observe(viewLifecycleOwner) { dogs ->
            dogAdapter.submitList(dogs)
        }
    }
}