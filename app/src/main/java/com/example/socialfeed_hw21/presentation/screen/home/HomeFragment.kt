package com.example.socialfeed_hw21.presentation.screen.home

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.socialfeed_hw21.databinding.FragmentHomeBinding
import com.example.socialfeed_hw21.presentation.base.BaseFragment
import com.example.socialfeed_hw21.presentation.event.PostsEvent
import com.example.socialfeed_hw21.presentation.extension.showToast
import com.example.socialfeed_hw21.presentation.state.PostsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(storeItemsState: PostsState) = with(binding) {
//        binding.progressBar.visibility =
//            if (storeItemsState.isLoading) View.VISIBLE else View.GONE

        storeItemsState.errorMessage?.let {
            root.showToast(storeItemsState.errorMessage)
            viewModel.onEvent(PostsEvent.ResetErrorMessage)
        }

        storeItemsState.data?.let {
            d("CHECK_DATA", it.toString())
        }
    }
}