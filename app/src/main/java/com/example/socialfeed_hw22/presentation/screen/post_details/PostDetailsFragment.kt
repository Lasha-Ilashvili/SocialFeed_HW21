package com.example.socialfeed_hw22.presentation.screen.post_details

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socialfeed_hw22.R
import com.example.socialfeed_hw22.databinding.PostItemBinding
import com.example.socialfeed_hw22.presentation.base.BaseFragment
import com.example.socialfeed_hw22.presentation.event.post_details.PostDetailsEvent
import com.example.socialfeed_hw22.presentation.extension.loadImage
import com.example.socialfeed_hw22.presentation.extension.resetMarginStart
import com.example.socialfeed_hw22.presentation.extension.showToast
import com.example.socialfeed_hw22.presentation.model.Post
import com.example.socialfeed_hw22.presentation.screen.post_details.adapter.PostImagesRecyclerViewAdapter
import com.example.socialfeed_hw22.presentation.state.post_details.PostDetailsState
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostDetailsFragment :
    BaseFragment<PostItemBinding>(PostItemBinding::inflate) {

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

        data?.let {
            setPostDetails(it)
        }
    }

    private fun setPostDetails(post: Post) {
        val owner = post.owner
        val username = "${owner.firstName} ${owner.lastName}"

        with(binding) {
            if (owner.profile.isNullOrBlank()) {
                ivPfp.visibility = View.GONE
                tvUsername.resetMarginStart()
            } else {
                ivPfp.loadImage(post.owner.profile)
            }

            tvUsername.text = username
            tvDate.text = owner.postDate
            tvCommentNumber.text = post.comments.toString()
            tvLikeNumber.text = post.likes.toString()
        }

        setImages(post.images)
    }

    private fun setImages(images: List<String>?) = with(binding) {
        images?.let {
            rvImages.layoutManager = LinearLayoutManager(context)

            rvImages.adapter = PostImagesRecyclerViewAdapter().apply {
                setData(it)
            }
        }
    }

    private fun hideBottomNavBar() {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.VISIBLE
    }
}