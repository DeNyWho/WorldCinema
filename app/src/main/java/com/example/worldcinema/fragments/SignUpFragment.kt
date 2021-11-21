package com.example.worldcinema.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.example.worldcinema.R
import com.example.worldcinema.retrofit.NetworkService
import kotlinx.coroutines.*

class SignUpFragment : Fragment() {

    @DelicateCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        val button = view.findViewById<AppCompatButton>(R.id.go_to_sign_in)
        val name = view.findViewById<EditText>(R.id.name)
        val second_name = view.findViewById<EditText>(R.id.second_name)
        val password = view.findViewById<EditText>(R.id.password)
        val again_password = view.findViewById<EditText>(R.id.again_password)
        val email = view.findViewById<EditText>(R.id.email)
        val singUp = view.findViewById<AppCompatButton>(R.id.sign_up)

        var _email = ""
        var _password = ""
        var _again_password = ""
        var _name = ""
        var _secondName = ""

        singUp.setOnClickListener {
            if (email.text.isNotEmpty()) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches())
                    _email = email.text.toString()
            } else
                email.error = "Введите email"

            if (password.text.isNotEmpty()) {
                _password = password.text.toString()
            } else
                password.error = "Введите пароль"

            if (again_password.text.isNotEmpty()) {
                _again_password = again_password.text.toString()
            } else
                again_password.error = "Введите пароль ещё раз"

            if (name.text.isNotEmpty()) {
                _name = name.text.toString()
            } else
                name.error = "Введите имя"

            if (second_name.text.isNotEmpty()) {
                _secondName = second_name.text.toString()
            } else
                second_name.error = "Введите фамилию"

            if (_password == _again_password && _name.isNotEmpty() && _secondName.isNotEmpty() && _password.isNotEmpty() && _again_password.isNotEmpty() && _email.isNotEmpty()) {

                if (_name.isNotEmpty() && _secondName.isNotEmpty() && _password.isNotEmpty() && _again_password.isNotEmpty() && _email.isNotEmpty()) {
                    GlobalScope.launch(Dispatchers.IO) {
                        val response = NetworkService.api().signUp(_email, _password, _name, _secondName)
                        withContext(Dispatchers.Main){

                            val token = response.body()?.token
                            if (token != null && token.toInt() != 400) {
                                findNavController().navigate(R.id.action_signOutFragment_to_mainFragment)
                            }
                            else
                                Toast.makeText(requireContext(), "Неавторизованный доступ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                password.error = "Пароль должен быть одиннаковым"
                again_password.error = "Пароль должен быть одиннаковым"
            }
        }


        button.setOnClickListener {
            findNavController().navigate(R.id.action_signOutFragment_to_signInFragment)
        }
        return view
    }

}