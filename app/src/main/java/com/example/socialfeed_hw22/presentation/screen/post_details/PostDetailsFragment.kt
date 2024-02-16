package com.example.socialfeed_hw22.presentation.screen.post_details

import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.socialfeed_hw22.R
import com.example.socialfeed_hw22.databinding.FragmentPostDetailsBinding
import com.example.socialfeed_hw22.presentation.base.BaseFragment
import com.example.socialfeed_hw22.presentation.event.post_details.PostDetailsEvent
import com.example.socialfeed_hw22.presentation.extension.showToast
import com.example.socialfeed_hw22.presentation.state.post_details.PostDetailsState
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostDetailsFragment :
    BaseFragment<FragmentPostDetailsBinding>(FragmentPostDetailsBinding::inflate) {

    private val args: PostDetailsFragmentArgs by navArgs()

    private val viewModel: PostDetailsViewModel by viewModels()

    override fun setUp() {
        hideBottomNavBar()

        viewModel.onEvent(PostDetailsEvent.SetPostDetails(args.id))
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postDetails.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(postDetailsState: PostDetailsState) = with(postDetailsState) {
//        binding.progressBar.visibility =
//            if (isLoading) View.VISIBLE else View.GONE

        errorMessage?.let {
            binding.root.showToast(errorMessage)
            viewModel.onEvent(PostDetailsEvent.ResetErrorMessage)
        }

//        if (!stories.isNullOrEmpty() && !posts.isNullOrEmpty()) {
//            binding.rvParent.adapter = HomeRecyclerViewAdapter().apply {
//                onClick = ::navigateToDetailsPage
//
//
//                setStories(stories)
//                setPosts(posts)
//            }
//        }

        data?.let {
            d("CHECK_DATA", it.toString())
        }
    }

    private fun hideBottomNavBar() {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.GONE
    }
}