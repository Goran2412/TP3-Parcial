package com.example.parcialtp3.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.parcialtp3.common.Result

import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.R

import com.example.parcialtp3.databinding.FragmentHomeBinding
import com.example.parcialtp3.ui.dogslist.DogsProvider
import com.example.parcialtp3.ui.dogslist.adapter.DogAdapter
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initRecyclerView(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.testInsert()

        viewModel.dogsListState.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    Log.d(TAG, "success!")
                    Log.d(TAG, "${it.data}")
                }

                is Result.Loading -> {
                    Log.d(TAG, "loading...")
                }

                is Result.Error -> {
                    Log.d(TAG, "error! ${it.message}")
                }

                else -> {}
            }
        }
        setupMenu()

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

                searchView.setOnSearchClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED).also {
        }
    }

    private fun initRecyclerView(view: View) {
        val rV = view.findViewById<RecyclerView>(R.id.dogs_rv)
        rV.layoutManager = LinearLayoutManager(this.context)
        context?.let {
            rV.adapter = DogAdapter(DogsProvider.mainPageDogs, it)
        }
    }

}