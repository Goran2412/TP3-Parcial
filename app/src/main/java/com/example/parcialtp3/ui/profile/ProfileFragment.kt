package com.example.parcialtp3.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parcialtp3.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment() {
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adoptionViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.username
        adoptionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Aquí es donde agregas el OnClickListener a tu botón
        val uploadButton: Button = binding.uploadButton
        val imageUrlInput: EditText = binding.imageUrlInput
        val sharedPreferences = requireActivity().getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE)

        uploadButton.setOnClickListener {
            if (imageUrlInput.visibility == View.GONE) {
                imageUrlInput.visibility = View.VISIBLE
                uploadButton.text = "Confirmar"
            } else {
                val imageUrl = imageUrlInput.text.toString()
                if (imageUrl.isNotEmpty()) {
                    val editor = sharedPreferences.edit()
                    editor.putString("ProfileImage", imageUrl)
                    editor.apply()

                    // Carga la imagen en el ImageView
                    Picasso.get()
                        .load(imageUrl)
                        .into(binding.profileImage)
                }

                imageUrlInput.visibility = View.GONE
                uploadButton.text = "Subir foto"
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE)
        val imageUrl = sharedPreferences.getString("ProfileImage", null)

        if (imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .into(binding.profileImage)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}