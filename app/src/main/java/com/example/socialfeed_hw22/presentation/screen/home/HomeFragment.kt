package com.example.socialfeed_hw22.presentation.screen.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.socialfeed_hw22.databinding.FragmentHomeBinding
import com.example.socialfeed_hw22.presentation.base.BaseFragment
import com.example.socialfeed_hw22.presentation.event.FeedEvent
import com.example.socialfeed_hw22.presentation.extension.showToast
import com.example.socialfeed_hw22.presentation.screen.home.adapter.HomeRecyclerViewAdapter
import com.example.socialfeed_hw22.presentation.state.FeedState
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

    private fun handleState(feedState: FeedState) = with(feedState) {
        binding.progressBar.visibility =
            if (isLoading) View.VISIBLE else View.GONE

        errorMessage?.let {
            binding.root.showToast(errorMessage)
            viewModel.onEvent(FeedEvent.ResetErrorMessage)
        }

        if (!stories.isNullOrEmpty() && !posts.isNullOrEmpty()) {
            binding.rvParent.adapter = HomeRecyclerViewAdapter().apply {
                setStories(stories)
                setPosts(posts)
            }
        }
    }
}