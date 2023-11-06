package com.example.parcialtp3.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import com.example.parcialtp3.R

class FilterDialogFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    //getSelectedItems y applyFilters se utiliza más abajo.
    fun getSelectedItems(listView: ListView): List<String> {
        val selectedItems = mutableListOf<String>()
        val checkedItemPositions = listView.checkedItemPositions
        for (i in 0 until checkedItemPositions.size()) {
            val position = checkedItemPositions.keyAt(i)
            if (checkedItemPositions.valueAt(i)) {
                val item = listView.adapter.getItem(position) as String
                selectedItems.add(item)
            }
        }
        return selectedItems
    }

    fun applyFilters(selectedBreeds: List<String>, selectedLocations: List<String>) {
        // Implementa la lógica de filtrado aquí
    }

    // datos hardcodeados para probar
    val breedList = listOf("Caniche", "Perro batata")

    // datos hardcodeados para probar
    val locationList = listOf("Córdoba", "Tucumán")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filter_dialog, container, false)

/*
        //referencias a los ListViews
        val breedListView = view.findViewById<ListView>(R.id.breedListView)
        val locationListView = view.findViewById<ListView>(R.id.locationListView)

        //adaptadores para las listas
        val breedAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_multiple_choice, breedList)
        val locationAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_multiple_choice, locationList)
*/
        val breedListView = view.findViewById<ListView>(R.id.breedListView)
        val locationListView = view.findViewById<ListView>(R.id.locationListView)

        val breedAdapter = CheckboxListAdapter(requireContext(), breedList)
        val locationAdapter = CheckboxListAdapter(requireContext(), locationList)

        breedListView.adapter = breedAdapter
        locationListView.adapter = locationAdapter


        // adaptadores asignados a los ListViews
        breedListView.adapter = breedAdapter
        locationListView.adapter = locationAdapter




        val applyFiltersButton = view.findViewById<Button>(R.id.applyFiltersButton)
        val sortByDateCheckBox = view.findViewById<CheckBox>(R.id.sortByDateCheckBox)


        applyFiltersButton.setOnClickListener {
            // Obtener las selecciones de los checkboxes
            val selectedBreeds = getSelectedItems(breedListView)
            val selectedLocations = getSelectedItems(locationListView)

            // Aplicar filtros en función de las selecciones
            applyFilters(selectedBreeds, selectedLocations)

            val sortByDate = sortByDateCheckBox.isChecked
            if (sortByDate) {
                // Apply sort by date
                // Implement your sort by date logic here
            }
            // Close the filter dialog
            dismiss()
        }







        return view
    }

}