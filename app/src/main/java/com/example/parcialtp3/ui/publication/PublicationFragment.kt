package com.example.parcialtp3.ui.publication

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parcialtp3.databinding.FragmentPublicationBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.parcialtp3.common.Provincias

@AndroidEntryPoint
class PublicationFragment: Fragment() {

    private var _binding: FragmentPublicationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adoptionViewModel =
            ViewModelProvider(this).get(PublicationViewModel::class.java)

        _binding = FragmentPublicationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configura el Spinner con los valores del enum
        val spinnerUbicacion: Spinner = binding.dogLocation
        val provinciasArray = Provincias.values().map { it.name }.toTypedArray()
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, provinciasArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUbicacion.adapter = adapter

        val textView: TextView = binding.dogName
        adoptionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 