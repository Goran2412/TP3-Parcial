package com.example.parcialtp3.ui.home

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
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parcialtp3.R
import com.example.parcialtp3.databinding.ChipBinding
import com.example.parcialtp3.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint



private const val TAG = "HomeListFragment"
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        setupChip()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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
        }, viewLifecycleOwner, Lifecycle.State.RESUMED).also {
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