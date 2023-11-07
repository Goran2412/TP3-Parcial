package com.example.parcialtp3.ui.favourites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcialtp3.R
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.databinding.FragmentFavouritesBinding
import com.example.parcialtp3.ui.adapter.adapter.DogListAdapter
import com.example.parcialtp3.ui.adapter.adapter.DogListener
import com.example.parcialtp3.ui.adapter.adapter.SaveIconListener


import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "FavouritesFragment"

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private val viewModel: FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = DogListAdapter(DogListener { dog, dogId ->
            Log.d(TAG, "dog${dog.name} id $dogId")
        },
            SaveIconListener { dogId ->
                Log.d(TAG, "$dogId")
                viewModel.updateDogFavouriteStatus(dogId)
            })

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dogsListState.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    handleLoading(false)
                    val adapter = binding.recyclerView.adapter as DogListAdapter
                    adapter.submitList(it.data)
                    Log.d(TAG, "checking favourite ${it.data} ")
                }

                is Result.Loading -> {
                    handleLoading(true)
                    Log.d(TAG, "loading...")
                }

                is Result.Error -> {
                    handleLoading(false)
                    Log.d(TAG, "error! ${it.message}")
                }

                else -> {}
            }
        }
    }
    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                progressBarHome.visibility = View.VISIBLE
            } else {
                progressBarHome.visibility = View.GONE
            }
        }
    }

}