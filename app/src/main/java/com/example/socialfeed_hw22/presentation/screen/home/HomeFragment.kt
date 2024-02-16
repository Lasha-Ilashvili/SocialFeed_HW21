package com.example.socialfeed_hw22.presentation.screen.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.socialfeed_hw22.databinding.FragmentHomeBinding
import com.example.socialfeed_hw22.presentation.base.BaseFragment
import com.example.socialfeed_hw22.presentation.event.home.HomeEvent
import com.example.socialfeed_hw22.presentation.extension.showToast
import com.example.socialfeed_hw22.presentation.screen.home.adapter.HomeRecyclerViewAdapter
import com.example.socialfeed_hw22.presentation.state.home.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.feed.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(feedState: HomeState) = with(feedState) {
        binding.progressBar.visibility =
            if (isLoading) View.VISIBLE else View.GONE

        errorMessage?.let {
            binding.root.showToast(errorMessage)
            viewModel.onEvent(HomeEvent.ResetErrorMessage)
        }

        if (!stories.isNullOrEmpty() && !posts.isNullOrEmpty()) {
            binding.rvParent.adapter = HomeRecyclerViewAdapter().apply {
                onClick = ::navigateToDetailsPage

                setStories(stories)
                setPosts(posts)
            }
        }
    }

    private fun navigateToDetailsPage(id: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToPostDetailsFragment(id = id)
        )
    }
}