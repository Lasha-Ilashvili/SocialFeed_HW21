package com.example.socialfeed_hw22.presentation.screen.post_details

import android.view.View
import com.example.socialfeed_hw22.R
import com.example.socialfeed_hw22.databinding.FragmentPostDetailsBinding
import com.example.socialfeed_hw22.presentation.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class PostDetailsFragment :
    BaseFragment<FragmentPostDetailsBinding>(FragmentPostDetailsBinding::inflate) {

    override fun setUp() {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.GONE
    }

}