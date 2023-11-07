package com.example.parcialtp3.ui.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialtp3.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "DetailsFragment"
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel : DetailsViewModel by viewModels()

    private lateinit var dogOwnerName: TextView
    private val dogOwnerPhoneNumber = "12345678"
    private lateinit var dogOwnerImage: ImageView
    private lateinit var dogNameText: TextView
    private lateinit var locationText: TextView
    private lateinit var dogAgeText: TextView
    private lateinit var dogGenreIcon: ImageView
    private lateinit var dogGenreText: TextView
    private lateinit var dogWeightText: TextView
    private lateinit var dogDescriptionText: TextView
    private lateinit var dogImagesRecyclerView : RecyclerView
    private lateinit var dogImages : List<DogImage>
    private lateinit var adapter : DogImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView?.visibility = View.GONE

        dogOwnerName = view.findViewById(R.id.dogOwnerName)
        dogOwnerImage = view.findViewById(R.id.dogOwnerImage)

        dogNameText = view.findViewById(R.id.dogNameText)
        locationText = view.findViewById(R.id.locationText)
        dogAgeText = view.findViewById(R.id.dogAgeText)
        dogGenreIcon = view.findViewById(R.id.dogGenreIcon)
        dogGenreText = view.findViewById(R.id.dogGenreText)
        dogWeightText = view.findViewById(R.id.dogWeightText)
        dogDescriptionText = view.findViewById(R.id.dogDescriptionText)

        getData()

        dogImagesRecyclerView = view.findViewById(R.id.dogImagesRecyclerView)

        adapter = DogImageAdapter(dogImages)
        dogImagesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        dogImagesRecyclerView.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(dogImagesRecyclerView)

        val callButton = view.findViewById<ImageButton>(R.id.callButton)
        callButton.setOnClickListener {
            callOwner()
        }

        val adoptButton = view.findViewById<Button>(R.id.adoptButton)
        adoptButton.setOnClickListener {
            viewModel.adoptDog()
            findNavController().navigateUp()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView?.visibility = View.VISIBLE
    }

    private fun callOwner() {

        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$dogOwnerPhoneNumber")

        startActivity(intent)
    }

    private fun adoptDog() {
//        val message = "TO DO: Adopcion del perro."
//        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
//        toast.show()

        }


    private fun getData() {

        //Images
//        val ownerImageUrl = "https://img.freepik.com/free-photo/portrait-white-man-isolated_53876-40306.jpg?size=626&ext=jpg&ga=GA1.1.1880011253.1699142400&semt=ais"
//        Picasso.get()
//            .load(ownerImageUrl)
//            .into(dogOwnerImage)

        val detailSharedPreferences = requireActivity().getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE)
        val imageUrl = detailSharedPreferences.getString("ProfileImage", null)

        if (imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .into(dogOwnerImage)
        } else{
            Picasso.get()
                .load(R.drawable.ic_profile)
                .into(dogOwnerImage)
        }

        val dogImageUrls = viewModel.dog.images ?: emptyList()
        dogImages = dogImageUrls.map { DogImage(it) }


        dogNameText.text = viewModel.dog.name ?: "Unknown"
        locationText.text = viewModel.dog.location ?: "Unknown"
        dogAgeText.text = viewModel.dog.age?.toString() ?: "Unknown"
        dogGenreText.text = viewModel.dog.gender ?: "Unknown"

        if (viewModel.dog.gender == "Macho") {
            dogGenreIcon.setImageResource(R.drawable.ic_male)
        } else {
            dogGenreIcon.setImageResource(R.drawable.ic_female)
        }

        dogWeightText.text = viewModel.dog.weight?.toString() ?: "Unknown"
        dogDescriptionText.text = viewModel.dog.description ?: "No description available"

        dogDescriptionText?.text = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Cumque laudantium iste accusantium id asperiores assumenda labore minima aut ut at ducimus, possimus, dolores tempore amet magni mollitia autem blanditiis nulla asperiores assumenda labore minima aut ut at ducimus"

        //Owner Details
        dogOwnerName?.text = "Juan Perez"
    }
}