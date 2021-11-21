package com.example.worldcinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHost.findNavController()
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.fragment)

        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}