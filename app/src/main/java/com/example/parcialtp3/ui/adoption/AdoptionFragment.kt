package com.example.parcialtp3.ui.adoption

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.databinding.FragmentAdoptionBinding

import com.example.parcialtp3.ui.adapter.adapter.DogListAdapter
import com.example.parcialtp3.ui.adapter.adapter.DogListener
import com.example.parcialtp3.ui.adapter.adapter.SaveIconListener


import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "AdoptionFragment"
@AndroidEntryPoint
class AdoptionFragment : Fragment() {

    private lateinit var binding: FragmentAdoptionBinding
    private val viewModel: AdoptionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentAdoptionBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = DogListAdapter(DogListener { dog, dogId ->
            Log.d(TAG, "dog${dog.name} id $dogId")
            //  viewModel.updateDogFavouriteStatus(dogId, !dog.isFavourite)
        },
            SaveIconListener {
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