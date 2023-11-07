package com.example.parcialtp3.ui.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.parcialtp3.R

class LandingPageFragment : Fragment() {

    private val images = listOf(R.drawable.ic_paws, R.drawable.ic_paws, R.drawable.ic_paws)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.landing_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // oculta la appbar
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        val roundButton: Button = view.findViewById(R.id.roundButton)

        //para viajar al homeFragment al apretar el boton
        roundButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        val viewPager: ViewPager2 = view.findViewById(R.id.imagesCarrousel)
        val adapter = ViewPagerAdapter(images)
        viewPager.adapter = adapter

        val indicadorUno: View = view.findViewById(R.id.itemUnoIndicador)
        val indicadorDos: View = view.findViewById(R.id.itemDosIndicador)
        val indicadorTres: View = view.findViewById(R.id.itemTresIndicador)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        indicadorUno.background = ContextCompat.getDrawable(requireContext(), R.drawable.round_indicator_selected)
                        indicadorDos.background = ContextCompat.getDrawable(requireContext(), R.drawable.round_indicator_unselected)
                        indicadorTres.background = ContextCompat.getDrawable(requireContext(), R.drawable.round_indicator_unselected)
                    }
                    1 -> {
                        indicadorUno.background = ContextCompat.getDrawable(requireContext(), R.drawable.round_indicator_unselected)
                        indicadorDos.background = ContextCompat.getDrawable(requireContext(), R.drawable.round_indicator_selected)
                        indicadorTres.background = ContextCompat.getDrawable(requireContext(), R.drawable.round_indicator_unselected)
                    }
                    2 -> {
                        indicadorUno.background = ContextCompat.getDrawable(requireContext(), R.drawable.round_indicator_unselected)
                        indicadorDos.background = ContextCompat.getDrawable(requireContext(), R.drawable.round_indicator_unselected)
                        indicadorTres.background = ContextCompat.getDrawable(requireContext(), R.drawable.round_indicator_selected)
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (requireActivity() as AppCompatActivity).supportActionBar?.show() // sino no se vuelve a mostrar la appbar al salir del fragment
    }
}
