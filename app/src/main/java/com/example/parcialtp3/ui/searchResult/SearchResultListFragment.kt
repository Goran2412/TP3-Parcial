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
import com.example.parcialtp3.ui.search.SearchViewModel

class SearchResultListFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultListBinding
    private val viewModel: SearchViewModel by viewModels()

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

        binding.recyclerView2.layoutManager = LinearLayoutManager(requireContext())

    }
    private fun handleSearchResults(results: List<String>) {
        // Actualiza el adaptador con los resultados de la búsqueda
        // Asegúrate de que el adaptador esté configurado para mostrar los resultados adecuadamente
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