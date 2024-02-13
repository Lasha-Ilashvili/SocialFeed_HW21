package com.example.socialfeed_hw21.presentation.screen.home

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.socialfeed_hw21.databinding.FragmentHomeBinding
import com.example.socialfeed_hw21.presentation.base.BaseFragment
import com.example.socialfeed_hw21.presentation.event.FeedEvent
import com.example.socialfeed_hw21.presentation.extension.showToast
import com.example.socialfeed_hw21.presentation.state.FeedState
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

    private fun handleState(feedState: FeedState) = with(binding) {
        feedState.errorMessage?.let {
            root.showToast(feedState.errorMessage)
            viewModel.onEvent(FeedEvent.ResetErrorMessage)
        }

        feedState.data?.let {
            it.post?.let {posts->
                d("CHECK_DATA_POST", posts.toString())
            }

            it.story?.let {stories->
                d("CHECK_DATA_POST", stories.toString())
            }
        }
    }
}