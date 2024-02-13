package com.example.socialfeed_hw21.presentation.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.socialfeed_hw21.R
import com.example.socialfeed_hw21.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.bottomNav) {
            selectedItemId = R.id.homeFragment

            val navHostFragment = supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment
            ) as NavHostFragment

            setupWithNavController(
                navHostFragment.navController
            )
        }
    }
}