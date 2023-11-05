package com.example.parcialtp3.ui.dogslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.R
import com.example.parcialtp3.ui.dogslist.adapter.DogAdapter

class DogsListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dogs_recycler_view, container, false)
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view: View) {
        val rV = view.findViewById<RecyclerView>(R.id.dogs_rv)
        rV.layoutManager = LinearLayoutManager(activity)
        context?.let {
            rV.adapter = DogAdapter(DogsProvider.mainPageDogs, it)
        }
    }

}
