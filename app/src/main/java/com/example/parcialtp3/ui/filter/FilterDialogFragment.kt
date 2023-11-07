package com.example.parcialtp3.ui.filter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.parcialtp3.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FilterDialogFragment : DialogFragment() {
    private val viewModel: FilterDialogViewModel by viewModels()

    private lateinit var breedAdapter: CheckboxListAdapter
    private lateinit var locationAdapter: CheckboxListAdapter
    private val breedList = mutableListOf<String>()
    private val locationList = mutableListOf<String>()
    private lateinit var breedListView: ListView
    private lateinit var locationListView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filter_dialog, container, false)

        breedListView = view.findViewById<ListView>(R.id.breedListView)
        locationListView = view.findViewById<ListView>(R.id.locationListView)

        breedAdapter = CheckboxListAdapter(requireContext(), breedList)
        locationAdapter = CheckboxListAdapter(requireContext(), locationList)




        breedListView.adapter = breedAdapter
        locationListView.adapter = locationAdapter

        val applyFiltersButton = view.findViewById<Button>(R.id.applyFiltersButton)
        val sortByDateCheckBox = view.findViewById<CheckBox>(R.id.sortByDateCheckBox)

        applyFiltersButton.setOnClickListener {
            val selectedBreeds = getSelectedItems(breedListView)
            val selectedLocations = getSelectedItems(locationListView)

            viewModel.applyFilters(selectedBreeds, selectedLocations)

            val sortByDate = sortByDateCheckBox.isChecked
            if (sortByDate) {
                // Perform sorting logic
            }
            dismiss()
        }

        fetchBreeds()
        fetchLocations()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun fetchBreeds() {
        viewModel.getDistinctBreedsAndSubbreeds().observe(viewLifecycleOwner) { (breeds, subbreeds) ->
            breedList.clear()
            breedList.addAll(breeds)
            breedAdapter = CheckboxListAdapter(requireContext(), breedList)
            breedListView.adapter = breedAdapter
            breedAdapter.notifyDataSetChanged()
        }
    }

    private fun fetchLocations() {
        viewModel.getDistinctLocations().observe(viewLifecycleOwner) { locations ->
            locationList.clear()

            locationList.addAll(locations)
            locationAdapter = CheckboxListAdapter(requireContext(), locationList)
            locationListView.adapter = locationAdapter
            locationAdapter.notifyDataSetChanged()
        }
    }

    private fun getSelectedItems(listView: ListView): List<String> {
        val selectedItems = mutableListOf<String>()
        val checkedItemPositions = listView.checkedItemPositions
        val adapter = listView.adapter as CheckboxListAdapter // Updated adapter

        if (checkedItemPositions != null) {
            for (i in 0 until checkedItemPositions.size()) {
                val position = checkedItemPositions.keyAt(i)
                if (checkedItemPositions.valueAt(i)) {
                    val item = adapter.getItem(position) as String
                    selectedItems.add(item)
                }
            }
        }

        return selectedItems
    }

    private fun selectItems(listView: ListView, selectedItems: List<String>) {
        val adapter = listView.adapter as CheckboxListAdapter // Updated adapter
        for (i in 0 until adapter.count) {
            val item = adapter.getItem(i) as String
            if (selectedItems.contains(item)) {
                listView.setItemChecked(i, true)
            }
        }
    }
}
