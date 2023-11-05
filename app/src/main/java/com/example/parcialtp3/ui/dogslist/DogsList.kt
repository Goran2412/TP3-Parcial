package com.example.parcialtp3.ui.dogslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.R
import com.example.parcialtp3.ui.dogslist.adapter.DogAdapter

class DogsList : AppCompatActivity() {

    //var lista = listOf("Uno", "Dos", "Tres", "Cuatro") // Esta debería ser la lista de perros

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.dogs_recycler_view)
        initRecyclerView()
    }

    fun initRecyclerView() {
        val rV = findViewById<RecyclerView>(R.id.dogs_rv)
        rV.layoutManager = LinearLayoutManager(this)
        rV.adapter = DogAdapter(DogsProvider.mainPageDogs)
    }
}