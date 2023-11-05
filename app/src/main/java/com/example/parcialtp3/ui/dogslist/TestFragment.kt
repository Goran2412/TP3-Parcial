package com.example.parcialtp3.ui.dogslist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class TestFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = Intent(activity, DogsList::class.java)
        startActivity(intent)
        activity?.finish()  // Opcional: cierra el Fragment actual si se desea
    }
}