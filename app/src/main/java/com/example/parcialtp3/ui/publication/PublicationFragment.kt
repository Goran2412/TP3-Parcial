package com.example.parcialtp3.ui.publication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parcialtp3.databinding.FragmentPublicationBinding
import dagger.hilt.android.AndroidEntryPoint

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