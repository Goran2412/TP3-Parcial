package com.example.parcialtp3.ui.profile

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

        uploadButton.setOnClickListener {
            if (imageUrlInput.visibility == View.GONE) {
                // El usuario ha tocado "Subir foto", así que mostramos el EditText y cambiamos el texto del botón a "Confirmar"
                imageUrlInput.visibility = View.VISIBLE
                uploadButton.text = "Confirmar"
            } else {
                // El usuario ha tocado "Confirmar", así que obtenemos la URL de la imagen del EditText y la usamos para actualizar la imagen
                val imageUrl = imageUrlInput.text.toString()
                // TODO: Usa imageUrl para actualizar tu imagen

                // Ocultamos el EditText y cambiamos el texto del botón de nuevo a "Subir foto"
                imageUrlInput.visibility = View.GONE
                uploadButton.text = "Subir foto"
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}