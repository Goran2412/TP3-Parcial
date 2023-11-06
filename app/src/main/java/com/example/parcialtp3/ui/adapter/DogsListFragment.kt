package com.example.parcialtp3.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.parcialtp3.R

class DogsListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dogs_recycler_view, container, false)
       // initRecyclerView(view)
        return view
    }

//    private fun initRecyclerView(view: View) {
//        val rV = view.findViewById<RecyclerView>(R.id.dogs_rv)
//        rV.layoutManager = LinearLayoutManager(this.context)
//        context?.let {
//            rV.adapter = DogListAdapter(DogsProvider.mainPageDogs, it)
//        }
//    }

}
