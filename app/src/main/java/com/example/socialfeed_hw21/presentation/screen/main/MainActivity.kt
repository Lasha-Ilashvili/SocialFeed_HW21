package com.example.socialfeed_hw21.presentation.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.socialfeed_hw21.R
import com.example.socialfeed_hw21.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.bottomNav) {
            selectedItemId = R.id.homeFragment

            setupWithNavController(
                findNavController(R.id.nav_host_fragment)
            )
        }
    }
}