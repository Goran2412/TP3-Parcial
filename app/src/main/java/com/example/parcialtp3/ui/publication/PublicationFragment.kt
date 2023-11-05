package com.example.parcialtp3.ui.publication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.parcialtp3.R
import com.example.parcialtp3.common.Provincias
import com.example.parcialtp3.common.Result
import com.example.parcialtp3.databinding.FragmentPublicationBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PublicationFragment"

@AndroidEntryPoint
class PublicationFragment : Fragment() {

    private lateinit var binding: FragmentPublicationBinding
    private val viewModel: PublicationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPublicationBinding.inflate(inflater, container, false)

        binding.dogLocation.setAdapter(generateAdapter())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllBreeds()

        viewModel.breedsListState.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    Log.d(TAG, "result $it")
                }

                is Result.Error -> {
                    Log.d(TAG, "HUBO UN ERROR ${it.message}")
                }

                is Result.Loading -> {
                    Log.d(TAG, "loading breeds...")
                }

                else -> { Unit}
            }
        }

        viewModel.selectedBreed.observe(viewLifecycleOwner) { selectedBreed ->
            val breedWithSubBreeds = viewModel.breedsList.value?.find { it.breedName == selectedBreed }
            if (breedWithSubBreeds != null && breedWithSubBreeds.subBreeds.isNotEmpty()) {
                // If the selected breed has sub-breeds, make dogSubBreedLayout visible
                binding.dogSubBreedLayout.visibility = View.VISIBLE
                // Populate dogSubBreed AutoCompleteTextView with sub-breeds
                val subBreedAdapter = ArrayAdapter(requireContext(), R.layout.list_type_enum, breedWithSubBreeds.subBreeds)
                binding.dogSubBreed.setAdapter(subBreedAdapter)
            } else {
                // If no sub-breeds, hide dogSubBreedLayout
                binding.dogSubBreedLayout.visibility = View.GONE
            }
        }

        viewModel.breedsList.observe(viewLifecycleOwner) { breedsWithSubBreeds ->
            val breedNames = breedsWithSubBreeds.map { it.breedName }
            val adapter = ArrayAdapter(requireContext(), R.layout.list_type_enum, breedNames)
            binding.dogBreed.setAdapter(adapter)
            binding.dogBreed.setOnItemClickListener { _, _, _, _ ->
                // When a breed is selected, set it as the selected breed
                viewModel.setSelectedBreed(binding.dogBreed.text.toString())
            }
        }
    }

    private fun generateAdapter(): ArrayAdapter<String> {
        val enumValues = getFormattedEnumValues(Provincias::class.java)
        return ArrayAdapter(requireContext(), R.layout.list_type_enum, enumValues)
    }

    private fun getFormattedEnumValues(enumClass: Class<out Enum<*>>): List<String> {
        return enumClass.enumConstants.map { it ->
            it.name.replace("_", " ").split(' ')
                .joinToString(" ") { it.lowercase().titleCaseFirstChar() }
        }
    }

    private fun String.titleCaseFirstChar(): String {
        return replaceFirstChar { it.titlecase() }
    }
}