package com.example.worldcinema.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.navigation.fragment.findNavController
import com.example.worldcinema.R

class LaunchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_launch, container, false)

        val handler = Handler()

        handler.postDelayed(3000){
            findNavController().navigate(R.id.to_signInFragment)
        }

        return view
    }

}