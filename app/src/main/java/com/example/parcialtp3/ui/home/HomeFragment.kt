package com.example.parcialtp3.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.parcialtp3.common.Result

import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider

import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcialtp3.R
import com.example.parcialtp3.databinding.ChipBinding

import com.example.parcialtp3.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import com.example.parcialtp3.ui.adapter.adapter.DogListAdapter
import com.example.parcialtp3.ui.adapter.adapter.DogListener
import com.example.parcialtp3.ui.adapter.adapter.SaveIconListener
import com.example.parcialtp3.ui.filter.CategorizedItem
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
        binding.recyclerView.adapter = DogListAdapter(DogListener { dog, dogId ->
            Log.d(TAG, "dog${dog.name} id $dogId")
            //  viewModel.updateDogFavouriteStatus(dogId, !dog.isFavourite)
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(dog))
        },
            SaveIconListener { dogId ->
                Log.d(TAG, "$dogId")
                viewModel.updateDogFavouriteStatus(dogId)
            })

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        val moreFiltersButton = binding.moreFiltersButton

        val text = SpannableString(getString(R.string.more_filters))

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_homeFragment_to_filterDialogFragment)
            }
        }
        text.setSpan(clickableSpan, 0, text.length, 0)

        moreFiltersButton.text = text

        moreFiltersButton.movementMethod = LinkMovementMethod.getInstance()

        val sharedPreferences = requireContext().getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val items = retrieveItemsFromSharedPreferences(sharedPreferences)

        // Log the retrieved data
        for (item in items) {
            val category = item.category
            val itemName = item.item
            val isChecked = item.isChecked

            Log.d(TAG, "Retrieved Data - Category: $category, Item: $itemName, Checked: $isChecked")

            // Do something with the retrieved data.
        }



        return binding.root

    }


    private fun retrieveItemsFromSharedPreferences(sharedPreferences: SharedPreferences): List<CategorizedItem> {
        val items = mutableListOf<CategorizedItem>()

        for (i in 0 until sharedPreferences.all.size) {
            val keyChecked = "selected_$i"
            val keyCategory = "category_$i"
            val keyItem = "item_$i"

            val isChecked = sharedPreferences.getBoolean(keyChecked, false)
            val category = sharedPreferences.getString(keyCategory, "") ?: ""
            val item = sharedPreferences.getString(keyItem, "") ?: ""

            items.add(CategorizedItem(category, item, isChecked))
        }

        return items
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        repeat(4) {
//            viewModel.testInsert()
//        }
//
     //   viewModel.testInsert()
//        viewModel.testInsert()
//        viewModel.testInsert()
//        viewModel.testInsert()

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
        setupMenu()
        setupChip()
    }



        // Retrieve checkbox states for locations


    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.search_menu, menu)

                val menuItem = menu.findItem(R.id.search)
                val searchView = menuItem.actionView as SearchView
                searchView.queryHint = getString(R.string.search_hint)

                searchView.setOnSearchClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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


    private fun setupChip() {

        val nameList =
            arrayListOf("Golden", "Salchicha", "Terrier")
        for (name in nameList) {
            val chip = createChip(name)
            binding.chipGroup.addView(chip)
        }

    }
    private fun createChip(label: String): Chip {
        val chip = ChipBinding.inflate(layoutInflater).root
        chip.text = label
        return chip
    }


}