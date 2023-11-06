package com.example.parcialtp3.ui.publication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.addTextChangedListener
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


        binding.buttonPublicar.setOnClickListener {
            val breed = binding.dogBreed.text.toString()
            val subBreed = binding.dogSubBreed.text.toString()

            // Check if breed layout is visible and validate the breed
            if (binding.dogBreedLayout.visibility == View.VISIBLE) {
                viewModel.validateBreed(breed)

                viewModel.isBreedValid.observe(viewLifecycleOwner) { isBreedValid ->
                    if (!isBreedValid) {
                        binding.dogBreedLayout.error = "La raza no puede estar vacia"
                    } else {
                        binding.dogBreedLayout.error = null
                    }
                }
            }

            // Check if sub-breed layout is visible and validate the sub-breed
            if (binding.dogSubBreedLayout.visibility == View.VISIBLE) {
                viewModel.validateSubBreed(subBreed)

                viewModel.isSubBreedValid.observe(viewLifecycleOwner) { isSubBreedValid ->
                    if (!isSubBreedValid) {
                        binding.dogSubBreedLayout.error = "La subraza no puede estar vacia"
                    } else {
                        binding.dogSubBreedLayout.error = null
                    }
                }
            }

            // Proceed with form submission only if both breed and sub-breed are valid
            if (binding.dogBreedLayout.visibility != View.VISIBLE || (viewModel.isBreedValid.value == true
                        && (binding.dogSubBreedLayout.visibility != View.VISIBLE || viewModel.isSubBreedValid.value == true))
            ) {
                Log.d(TAG, "form is valid")
                // Form is valid, proceed with submission
                // You can move the submission logic here
            }
        }

        initObservers()
    

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

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                progressBarPublication.visibility = View.VISIBLE
                publicationForm.visibility = View.GONE
            } else {
                progressBarPublication.visibility = View.GONE
                publicationForm.visibility = View.VISIBLE
            }
        }
    }

    private fun initObservers(){

        viewModel.breedsListState.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    handleLoading(false)
                    Log.d(TAG, "result $it")
                }

                is Result.Loading -> handleLoading(true)
                is Result.Error -> handleLoading(false)

                else -> {
                    Unit
                }
            }
        }

        viewModel.selectedBreed.observe(viewLifecycleOwner) { selectedBreed ->
            val breedWithSubBreeds =
                viewModel.breedsList.value?.find { it.breedName == selectedBreed }
            if (breedWithSubBreeds != null && breedWithSubBreeds.subBreeds.isNotEmpty()) {
                // If the selected breed has sub-breeds, make dogSubBreedLayout visible
                binding.dogSubBreedLayout.visibility = View.VISIBLE
                // Populate dogSubBreed AutoCompleteTextView with sub-breeds
                val subBreedAdapter = ArrayAdapter(
                    requireContext(),
                    R.layout.list_type_enum,
                    breedWithSubBreeds.subBreeds
                )
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
                val selectedBreed = binding.dogBreed.text.toString()
                viewModel.setSelectedBreed(selectedBreed)

                // Clear the selected sub-breed
                binding.dogSubBreed.setText(
                    "",
                    false
                ) // Clear the text without triggering the AutoComplete dropdown
            }
        }
    }
}