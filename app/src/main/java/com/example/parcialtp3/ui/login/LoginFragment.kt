package com.example.parcialtp3.ui.login

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.parcialtp3.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textInputNombre = view.findViewById<TextInputEditText>(R.id.editTextNombre)

        textInputNombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // None
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString()
                if (!isValidEmail(email)) {
                    textInputNombre.setTextColor(Color.RED)
                    textInputNombre.error = "Dirección de correo no válida"
                } else {
                    textInputNombre.setTextColor(Color.BLACK)
                    textInputNombre.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // None
            }
        })

        val textInputLayoutContrasenia = view.findViewById<TextInputLayout>(R.id.textInputPassword)
        val textInputContrasenia = textInputLayoutContrasenia.findViewById<TextInputEditText>(R.id.editTextPassword)

        textInputContrasenia.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // None
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString()
                if (!isValidPassword(password)) {
                    textInputContrasenia.setTextColor(Color.RED)
                    textInputContrasenia.error = "Contraseña inválida"
                } else {
                    textInputContrasenia.setTextColor(Color.BLACK)
                    textInputContrasenia.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // None
            }
        })

        val boton = view.findViewById<Button>(R.id.btnIngreso)

        boton.setOnClickListener{
            val email = textInputNombre.text.toString()
            val password = textInputContrasenia.text.toString()

            if (isValidEmail(email) && isValidPassword(password)) {
                findNavController().navigate(R.id.homeFragment)
            } else {
                Toast.makeText(context, "Credenciales inválidas. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return (password.length >= 6 && password.isNotBlank())
    }
}
