package com.example.parcialtp3.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.databinding.FragmentHomeBinding
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
    }
}