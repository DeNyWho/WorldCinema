package com.example.worldcinema.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.example.worldcinema.R
import com.example.worldcinema.retrofit.ApiService
import com.example.worldcinema.retrofit.NetworkService
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class SignInFragment : Fragment() {

    @DelicateCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        val button = view.findViewById<AppCompatButton>(R.id.signUp)
        val buttonSign = view.findViewById<AppCompatButton>(R.id.signIn)

        var _email = ""
        var _password = ""

        buttonSign.setOnClickListener {
            if(email.text.isNotEmpty())
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches())
                    _email = email.text.toString()
            else
                email.error = "вы не ввели e-mail адресс"

            if(password.text.isNotEmpty())
                _password = password.text.toString()
            else
                password.error = "вы не ввели пароль"

            if(_email.isNotEmpty() && _password.isNotEmpty()){

                GlobalScope.launch(Dispatchers.IO){

                    try{
                        val response = NetworkService.api().signIn(_email, _password)

                        withContext(Dispatchers.Main){

                            val token = response.body()?.token
                            if (token != null && token.toInt() != 400) {
                                findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
                            }
                            else {
                                Toast.makeText(
                                    requireContext(),
                                    "Неавторизованный доступ",
                                    Toast.LENGTH_SHORT
                                ).show()
                                findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
                            }
                        }
                    }
                    catch (e: Exception) {
                        Log.e("Main","Error ${e.message}")
                    }

                }

            }

        }

        button.setOnClickListener {
            findNavController().navigate(R.id.to_signOutFragment)
        }
        return view
    }

}