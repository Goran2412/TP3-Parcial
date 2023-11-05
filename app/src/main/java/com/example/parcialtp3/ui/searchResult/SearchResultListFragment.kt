package com.example.parcialtp3.ui.searchResult

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcialtp3.databinding.FragmentSearchResultListBinding

class SearchResultListFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultListBinding
    private val viewModel: SearchResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(this.context)

        }


    }

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                progressBar2.visibility = View.VISIBLE
            } else {
                progressBar2.visibility = View.GONE
            }
        }
    }
}